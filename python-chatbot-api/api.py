# api.py
from flask import Flask, request, jsonify
import re

# Import the working functions from your storeon_bot.py script
from storeon_bot import generate_sql_directly, query_database, get_final_response

app = Flask(__name__)

@app.route('/ask', methods=['POST'])
def ask_bot():
    # Get the user's message from the incoming JSON request
    user_message = request.json.get('message')

    if not user_message:
        return jsonify({"error": "No message provided"}), 400

    try:
        # --- This is the reliable logic from your run_storeon_bot() function ---
        sql_query_raw = generate_sql_directly(user_message)

        # Extract only the SQL from the raw response
        match = re.search(r"```sql\n(.*)```", sql_query_raw, re.DOTALL)
        if match:
            sql_query = match.group(1).strip()
        elif "SELECT" in sql_query_raw.upper():
            # Take only the part of the string up to the semicolon
            sql_query = sql_query_raw.split(';')[0].strip()
        else:
            sql_query = None

        if not sql_query:
            # If no SQL is generated, get a direct LLM response without database info
            final_answer = get_final_response(user_message, "[]") # Pass empty data
        else:
            # Otherwise, execute the query and format the answer
            db_result = query_database(sql_query)
            final_answer = get_final_response(user_message, db_result)

        # Return the final answer as JSON
        return jsonify({"reply": final_answer})

    except Exception as e:
        print(f"An error occurred: {e}")
        return jsonify({"error": "An internal error occurred"}), 500

if __name__ == '__main__':
    # Run the Flask app on port 5000
    app.run(debug=True, host='0.0.0.0', port=5000)