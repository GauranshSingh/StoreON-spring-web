package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.SearchRepository;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class Search_bar_Controller {
	
	@Autowired
	private SearchRepository search_product;
	
	@GetMapping("/Search")
	public String findproduct(HttpSession session, Model model, @RequestParam String name) {
		
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		model.addAttribute("loggedInUser",loggedInUser);
		if(loggedInUser==null) {
			return"redirect:/login_page.html"; 
		}
		
//		System.out.println("name = "+name);
		
		List<ProductDetails> products = search_product.findByName(name);

//		for(ProductDetails c: products) {
//			System.out.println("product's name = " + c.getName());
//		}

		model.addAttribute("products", products);
		return "Search_result";
		
	}

}