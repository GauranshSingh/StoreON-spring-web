package com.gauransh.StoreON.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.*;		// this is the best way...only use * 

@Controller
public class LogoutStoreOn {
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("1");
		session.invalidate();
		System.out.println("2");		
		return "redirect:/login_page.html";
	}
}
