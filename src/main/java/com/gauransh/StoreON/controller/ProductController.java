package com.gauransh.StoreON.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@GetMapping("/product")
	public String getproduct(Model model, HttpSession session) {
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		if(loggedInUser==null) {
			return"redirect:/login_page.html";
		}
		model.addAttribute("loggedInUser",loggedInUser);
		return "product";
	}
}
