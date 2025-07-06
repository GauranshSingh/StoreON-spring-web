package com.gauransh.StoreON.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gauransh.StoreON.entity.ExtractUser;
import com.gauransh.StoreON.repository.ExtractUserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ExtractUserController {

    @Autowired
    private ExtractUserRepository extractUser;

    @GetMapping("/Webpage")
    public String getAllUsers(Model model,HttpSession session){
    	String loggedInUser = session.getAttribute("loggedInUser").toString();
    	String lastname =session.getAttribute("user_lastname").toString();
        List<ExtractUser> users = extractUser.findAll();		// these users are actually products_categories...I just named them wrong
    	model.addAttribute("lastname",lastname);
        model.addAttribute("loggedInUser",loggedInUser);
        model.addAttribute("users", users);						// these users are actually products_categories...I just named them wrong
        return "Webpage";  // takes to Webpage.html
    }
}