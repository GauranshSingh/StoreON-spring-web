package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.repository.CartRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class Add2CartController {
	
	@Autowired
	private CartRepository CartProduct;
	
	@PostMapping("/Add2Cart")
	public String getdata(HttpSession session, Model model,@RequestParam Integer productId){
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		if(loggedInUser==null) {
			return "redirect:/login_page.html";
		}
		model.addAttribute("loggedInUser",loggedInUser);
		Integer user_id =(Integer) session.getAttribute("user_id");
		
		CartProduct.AddintoCart(user_id,productId);
		
		

		return "redirect:/MyCart";
	}
}

