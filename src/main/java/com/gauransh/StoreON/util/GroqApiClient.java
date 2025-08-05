package com.gauransh.StoreON.util;

import java.io.IOException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GroqApiClient {

    @Value("${groq.api.key}")
    private String apiKey;

    public String getReply(String userMessage) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String json = """
        {
          "model": "llama3-8b-8192",
          "messages": [
            {
              "role": "system",
              "content": "you are StoreOn's AI Assistant, here to help users with e-commerce queries. Be helpful and concise. the year is 2025, and accordingly phones have been released like iphone 16 and stuff also the price on storeon website is the same as on the net so reply for price and discription accrodingly"
            },
            {
              "role": "user",
              "content": "%s"
            }
          ]
        }
        """.formatted(userMessage);

        Request request = new Request.Builder()
                .url("https://api.groq.com/openai/v1/chat/completions")		//url of groq
                 .header("Authorization", "Bearer "+ apiKey)	// it is to send api key and header
                .header("Content-Type", "application/json")
               .post(RequestBody.create(json, MediaType.get("application/json")))		//post method for json 
                .build();

        try (Response response = client.newCall(request).execute()) {       // 
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code: " + response);		// to handle error
            }
            
            return new JSONObject(response.body().string())
                     .getJSONArray("choices")		// array of only one choice
                     .getJSONObject(0)				// object inside the choices array(since there is only 2 object)
                    .getJSONObject("message")		// message like iphone 16 price?
                    .getString("content");			// and of the price
        }
    }
}
