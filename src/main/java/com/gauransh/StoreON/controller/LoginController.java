// this file is teh one that connects the user form entity to the repository that has the data in it

package com.gauransh.StoreON.controller;//again tells where is class 

import org.springframework.beans.factory.annotation.Autowired;      // to get auto wired function access
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.Login_User;
import com.gauransh.StoreON.repository.Login_UserRepository;				// to get user repository function access

import jakarta.servlet.http.HttpSession;

// I used autowired and controllwe so it can handle the http ...(saturday gone)
@Controller							
//its giving me the data form website
public class LoginController {
    @Autowired                     // to use repository(sql functions here) for sql                             // connect b/w data table and login by entity this hkelps to connect 
    private Login_UserRepository userRepository;
    @PostMapping("/login")					//this is the file that attached to my html loginpage from(post) it had form(l eg-    <form action="http://localhost:8090/login" method="post">
    public String login_website_GS(@RequestParam String email, @RequestParam String password,HttpSession session, Model model) {
        Login_User user = userRepository.findByEmail(email);		// finds the user
        if (user != null && user.getPassword().equals(password)) {
        	session.setAttribute("loggedInUser",user.getFirstName());
        	session.setAttribute("user_lastname", user.getLastName());
        	session.setAttribute("user_id",user.getUserId());
        	session.setAttribute("user", user);
//        	String loggedInUser = session.getAttribute("loggedInUser"); //should i save this in entity
//        	model.addAttribute("loggedInUser",loggedInUser);
            return "redirect:/Webpage"; //  This now works
        } else {
            return "redirect:/Incorrect.html"; // Redirect back to login if failed
        }
    }
}