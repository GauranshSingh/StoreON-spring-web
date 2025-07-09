package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.ProductDetailsRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	@Autowired
	private ProductDetailsRepository productdetailsrepository;
	
	@GetMapping("/product")
	public String getproduct(Model model, HttpSession session,@RequestParam Integer productId) {
		ProductDetails product = productdetailsrepository.findByProductId(productId);
		model.addAttribute("product",product);
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		model.addAttribute("loggedInUser",loggedInUser);
		if(loggedInUser==null) {
			return"redirect:/login_page.html"; 
		}
		product.setHighlights(					// added to get HIghlights in different lines
				product.getHighlights().replace("\n","<br>")
				);
		System.out.println("Saving to cart: userId = " + loggedInUser + ", productId = " + productId);
		return "product";
	}
}