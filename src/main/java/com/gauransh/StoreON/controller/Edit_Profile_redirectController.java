package com.gauransh.StoreON.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gauransh.StoreON.entity.Login_User;

import jakarta.servlet.http.HttpSession;

@Controller
public class Edit_Profile_redirectController {
	@GetMapping("/Edit_Profile.html")
	public String showEditProfilePage(HttpSession session, Model model) {
		System.out.println("Step: 1");
	    Login_User user = (Login_User) session.getAttribute("user");
	    System.out.println("user Name = "+ user.getFirstName());
	    model.addAttribute("user", user);
	    return "Edit_Profile";
	}
}
