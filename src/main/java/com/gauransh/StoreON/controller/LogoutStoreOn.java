package com.gauransh.StoreON.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.*;		// this is the best way...only use * 

@Controller
public class LogoutStoreOn {
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login_page.html";
	}
}
