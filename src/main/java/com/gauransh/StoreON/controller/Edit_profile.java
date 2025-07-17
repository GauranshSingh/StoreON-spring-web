package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.Login_User;
import com.gauransh.StoreON.repository.Update_profile;

import jakarta.servlet.http.HttpSession;

@Controller
public class Edit_profile {

	@Autowired
	private Update_profile Update;

	@PostMapping("/Edit_profile")
	public String Edit_your_profile( HttpSession session, Model model, @RequestParam String first_name, @RequestParam String last_name , @RequestParam String email, @RequestParam String ph_number, @RequestParam String address, @RequestParam String password){
//		Integer UserID = (Integer) session.getAttribute("user_id");
		
		
		Login_User user = (Login_User) session.getAttribute("user");
		
	    model.addAttribute("user", user);
	    
		String userIdStr = user.getUserId().toString();
		
		Update.Update_profile_funciton(userIdStr,first_name,last_name,email,ph_number,address,password);

		return "redirect:/Webpage";
		
	}
}
