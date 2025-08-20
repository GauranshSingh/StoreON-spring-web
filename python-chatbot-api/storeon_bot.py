import os
import re
import psycopg2
from dotenv import load_dotenv
from langchain_community.utilities import SQLDatabase
from langchain.chains import create_sql_query_chain
from langchain_groq import ChatGroq
from langchain_community.tools.sql_database.tool import QuerySQLDatabaseTool
from langchain.schema import SystemMessage, HumanMessage

# ====== LOAD ENV VARS ======
load_dotenv()

# ====== AUTO-FETCH SCHEMA ======
def get_db_schema_info():
    """Fetch all public tables and columns from PostgreSQL."""
    conn = psycopg2.connect(
        host="localhost",
        database="my_new_db",  # change if needed
        user="postgres",       # change if needed
        password="Jaswant1971" # change if needed
    )
    cur = conn.cursor()
    # ... (rest of the function is fine, no changes needed)
    cur.execute("""
        SELECT table_name
        FROM information_schema.tables
        WHERE table_schema = 'public'
        ORDER BY table_name;
    """)
    tables = cur.fetchall()

    schema_info = []
    for table in tables:
        table_name = table[0]
        cur.execute("""
            SELECT column_name, data_type
            FROM information_schema.columns
            WHERE table_name = %s
            ORDER BY ordinal_position;
        """, (table_name,))
        columns = cur.fetchall()

        column_list = ", ".join([f"{col[0]} ({col[1]})" for col in columns])
        schema_info.append(f"Table: {table_name}\nColumns: {column_list}")

    cur.close()
    conn.close()
    return "\n\n".join(schema_info)


# ====== FETCH SCHEMA ======
schema_description = get_db_schema_info()

# ====== STATIC PROMPT (No changes needed here) ======
SYSTEM_PROMPT = f"""You are StoreON Assistant, a helpful AI for an e-commerce store.
You have **strict rules**:
- You ONLY answer questions about the store's products, prices, categories, and availability.
- If a question is not about the store, say: "I'm only able to answer questions about StoreON products, prices, and availability."
- Never provide general knowledge.

Database schema:
{schema_description}

---
# Core Table Information:
- Use the 'list' table for simple price lookups by product name.
- Use 'product_details' table for detailed product info (offers, description, etc.). The 'new_price' column contains the current selling price.
- Do NOT query or expose sensitive user data from tables like 'cart', 'orders', or 'comment_table' unless specifically asked for non-sensitive info.

---
# ---
# Query Generation Rules:
# - You MUST ALWAYS generate a valid SQL query for any product-related question. NEVER respond with an apology or a refusal in place of a query.
# - If you are uncertain that a product exists, your duty is to generate the most likely SQL query anyway. The database will handle cases where the product is not found.
# - SQL queries MUST be highly specific. For a product like 'Samsung Galaxy S25', use a precise WHERE clause like `WHERE name ILIKE '%samsung%galaxy%s25%'`.
# - For comparisons involving multiple products, retrieve all relevant products in a single query using 'OR'.
# - Strictly select ONLY the columns needed to answer the user's question (e.g., `name`, `new_price`, `old_price`, `offers`). NEVER use `SELECT *`.
# - Your output MUST be only the SQL command. Do not add explanations, greetings, or any other text.  # <--- NEW RULE HERE
# ---
---
---
# Final Output Formatting Rules:
- After getting the data, present it clearly and concisely.
- ALWAYS start with a helpful introductory sentence.
- Use Markdown for formatting (like **bolding** for titles).
- When providing details for a single product, use a clear structure with bullet points, for example:
  **[Product Name]**
  • Price: [Price from new_price column]
  • Offers: [List of offers]
  • Highlights: [List of highlights]
- If comparing products, use a separate section for each product.
- ALWAYS calculate any requested values, like a price difference, and state it clearly at the end.
"""

# ====== LLM & DB SETUP ======
db = SQLDatabase.from_uri(
    "postgresql+psycopg2://postgres:Jaswant1971@localhost:5432/my_new_db",
    sample_rows_in_table_info=3
)

llm = ChatGroq(
    model="llama3-8b-8192",
    temperature=0,
    api_key=os.getenv("GROQ_API_KEY")
)

# Use the updated tool name to fix the deprecation warning
execute_query = QuerySQLDatabaseTool(db=db)

# ====== HELPERS ======

# ✅ THIS FUNCTION IS UPDATED WITH ERROR LOGGING
def query_database(sql_query):
    """Run SQL safely."""
    try:
        result = execute_query.invoke(sql_query)
        return result
    except Exception as e:
        # This will now print the specific technical error to the terminal
        print(f"\n[DATABASE ERROR] An exception occurred: {e}\n")
        return f"Error running SQL: {e}"


def generate_sql_directly(question: str) -> str:
    """Uses the main LLM and system prompt to generate SQL directly."""
    messages = [
        SystemMessage(content=SYSTEM_PROMPT),
        HumanMessage(content=question)
    ]
    response = llm.invoke(messages)
    return response.content

# ====== BOT LOOP ======
def is_small_talk(user_input):
    small_talk_phrases = ["hi", "hello", "hey", "your name", "how are you", "help me", "who are you"]
    return any(phrase in user_input.lower() for phrase in small_talk_phrases)

def get_final_response(question, db_result):
    """Takes the user question and DB result to generate a final, formatted response."""
    # This prompt now includes the same strict rules as the main prompt.
    synthesis_prompt = f"""You are StoreON Assistant. Your ONLY function is to answer questions based on the database information provided.

    **VERY STRICT RULES:**
    - You ONLY answer using the "Database Information" below.
    - If the "Database Information" is empty or does not contain the answer, you MUST say you cannot find the product information.
    - NEVER provide general knowledge, news, or information from outside the provided database context. Do not mention that a product is not real.
    - Format the answer clearly for the user.

    Here is the user's question and the data retrieved from the database:

    User's Question:
    "{question}"

    Database Information:
    "{db_result}"

    Based ONLY on the database information, provide a clear and structured answer.
    """
    
    return llm.invoke([
        SystemMessage(content="You are a helpful e-commerce assistant that presents information clearly based ONLY on provided data."),
        HumanMessage(content=synthesis_prompt)
    ]).content

def classify_query(user_input):
    """Check if query should go to SQL."""
    keywords = [
        "price", "available", "in stock", "cost", "how much",
        "quantity", "product", "buy", "list", "offer", "details", "amount", "any"
    ]
    return any(word in user_input.lower() for word in keywords)

# ✅ THIS FUNCTION IS UPDATED WITH ERROR LOGGING
def run_storeon_bot():
    print("StoreON Assistant is ready! (Type 'exit' to quit)\n")
    while True:
        user_input = input("You: ").strip()
        if user_input.lower() in ["exit", "quit"]:
            break

        if classify_query(user_input):
            sql_query_raw = generate_sql_directly(user_input)
            
            match = re.search(r"```sql\n(.*)```", sql_query_raw, re.DOTALL)
            if match:
                sql_query = match.group(1).strip()
            else:
                if "SELECT" in sql_query_raw.upper():
                    sql_query = sql_query_raw.strip()
                else:
                    sql_query = None

            if not sql_query:
                print(f"Assistant: I had trouble understanding that. Could you please rephrase your question about our products?")
                continue

            print(f"\n[DEBUG] Generated SQL:\n{sql_query}")
            db_result = query_database(sql_query)
            
            if "Error" not in str(db_result):
                final_answer = get_final_response(user_input, db_result)
                print(f"Assistant: {final_answer}")
            else:
                # This will now print the detailed error from the database
                print(f"Assistant: I encountered a problem. Here is the technical error: {db_result}")

        elif is_small_talk(user_input):
            response = llm.invoke([
                SystemMessage(content="You are StoreON Assistant. Answer in a friendly, short way for casual conversation."),
                HumanMessage(content=user_input)
            ]).content
            print(f"Assistant: {response}")
        else:
            print("Assistant: I'm only able to answer questions about StoreON products, prices, and availability.")

if __name__ == "__main__":
    run_storeon_bot()