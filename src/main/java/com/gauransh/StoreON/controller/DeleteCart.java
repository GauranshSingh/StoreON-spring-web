package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.repository.Delete_from_CartRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DeleteCart {

	@Autowired
	private Delete_from_CartRepository product_2_b_deleted;
	
	@GetMapping("/DeleteCart")
	public String delete_Product(HttpSession session, Model model, @RequestParam Integer productId) {
		
		Integer user_id =(Integer) session.getAttribute("user_id");

		product_2_b_deleted.delete_product_fromcart(productId,user_id);
		
		return "redirect:/MyCart";

	}
	
}
