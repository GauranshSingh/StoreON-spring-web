package com.gauransh.StoreON.util;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GroqApiClient {

    // The API key is no longer needed here as it's handled by the Python script
    // @Value("${groq.api.key}")
    // private String apiKey;

    public String getReply(String userMessage) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // 1. Create a simpler JSON payload for our Python API
        JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("message", userMessage);
        String json = jsonPayload.toString();

        // 2. Change the URL to point to our local Python API
        String pythonApiUrl = "http://localhost:5000/ask";

        Request request = new Request.Builder()
                .url(pythonApiUrl)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(json, MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // Provide a more specific error if the Python API isn't running
                throw new RuntimeException("Failed to connect to the Python bot API. Is the api.py script running? Response: " + response);
            }
            
            // 3. Parse the simpler response from our Python API
            return new JSONObject(response.body().string())
                     .getString("reply");
        }
    }
}