package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.repository.Add2CartRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class Category_Controller {
	
	@Autowired
	private Add2CartRepository object;
	
	@GetMapping
	public String getProduct(HttpSession session, Model model, @RequestParam Integer ProductId) {
		
		Integer UserID = (Integer) session.getAttribute("user_id");
		
		object.add2cart(UserID,ProductId);
		
		return "Mycart";
	}

}
