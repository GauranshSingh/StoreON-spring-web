package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gauransh.StoreON.util.GroqApiClient;


@Controller
public class ChatController {

    @GetMapping("/chatbot")
    public String openChatPage() {
        return "chat"; // chat.html file in your templates folder
    }

    @Autowired
    private GroqApiClient groqApiClient;


@PostMapping("/ask_bot")
@ResponseBody 
public String askBot(@RequestParam("userMessage") String userMessage) {
    try {
        String botReply = groqApiClient.getReply(userMessage);
        return botReply;
    } catch (Exception e) {
        return "Sorry, I couldn't respond. Try again!";
    }
}
}
