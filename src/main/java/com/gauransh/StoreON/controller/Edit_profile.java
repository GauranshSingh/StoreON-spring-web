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
		System.out.println("Step: 2");
//		Integer UserID = (Integer) session.getAttribute("user_id");
		
		
		Login_User user = (Login_User) session.getAttribute("user");
	    model.addAttribute("user", user);
	    System.out.println("user Name = "+ user.getFirstName());
		String userIdStr = user.getUserId().toString();
		System.out.println("user = " + userIdStr);
		Integer a = Update.Update_profile_funciton(userIdStr,first_name,last_name,email,ph_number,address,password);
		System.out.println("Step: 3");

		System.out.println("value = " + a);
		System.out.println("Step: 4");

		return "redirect:/Webpage";
		
	}
}
