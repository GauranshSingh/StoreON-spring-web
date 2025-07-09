package com.gauransh.StoreON.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gauransh.StoreON.entity.Cart;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.MyCartRepository;
import com.gauransh.StoreON.repository.Product_details_ListRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyCartController {

	@Autowired
	private MyCartRepository productdetailsrepository;
	
	@Autowired 
	private Product_details_ListRepository ProductDetailsCart;
	
	@GetMapping("/MyCart")
	public String getproduct(Model model, HttpSession session) {
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		if(loggedInUser==null) {
			return "redirect:/login_page.html";
		}
		model.addAttribute("loggedInUser",loggedInUser);
		

		
		
		
		Integer UserId=(Integer) session.getAttribute("user_id");
		List<Cart> ProductsinCart = productdetailsrepository.findByUserId(UserId);
		
//		System.out.println("---- ProductsinCart ----");
//	    for (Cart c : ProductsinCart) {
//	        System.out.println("ProductsinCart: " + ProductsinCart);
//	    }
		
		
	
		List<Integer> productIds = ProductsinCart.stream().map(Cart::getProductId).toList();
		
//		System.out.println("---- productIds ----");
//	    for (Cart c : ProductsinCart) {
//	        System.out.println("productIds: " + productIds);
//	    }
		
		
		
		List<ProductDetails> Cart_Product_Details = ProductDetailsCart.findByProductIdIn(productIds);

//	    System.out.println("---- Cart_Product_Details ----");
//	    for (Cart c : ProductsinCart) {
//	        System.out.println("Cart_Product_Details: " + Cart_Product_Details);
//	    }
		
	    
	    
		
	    model.addAttribute("cartProducts", Cart_Product_Details);

//	    System.out.println("---- CART ITEMS ----");
//	    for (Cart c : ProductsinCart) {
//	        System.out.println("User ID: " + c.getUserId() + ", Product ID: " + c.getProductId());
//	    }

	    
	    
		return "MyCart";
	}
}