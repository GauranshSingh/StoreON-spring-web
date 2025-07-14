package com.gauransh.StoreON.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
	
		List<Integer> productIds = ProductsinCart.stream().map(Cart::getProductId).toList();
		
		
		if(productIds.isEmpty()) {
			return "empty_cart";
			
		}

		
		List<ProductDetails> Cart_Product_Details = ProductDetailsCart.findByProductIdIn(productIds);
		
		
	    Map<Integer, Integer> quantityMap = new HashMap<>();

	    for (Cart c : ProductsinCart) {
	        int productId = c.getProductId();
	        quantityMap.put(productId, quantityMap.getOrDefault(productId, 0) + 1);
	    }

	    double overall_price =0;
	    int overall_quantity = 0;
	    	    	    
	    for(ProductDetails c : Cart_Product_Details) {
	    	 int productId = c.getProductId();
	    	 int quantity = quantityMap.getOrDefault(productId, 0); // get quantity from map 
	    	 overall_quantity=overall_quantity+quantity;
	    	 overall_price = overall_price + c.getNewPrice()*quantity;

	    }
	    
	    model.addAttribute("cartProducts", Cart_Product_Details);
	      
	    model.addAttribute("quantityMap", quantityMap);  
			    
	    model.addAttribute("overall_price",overall_price);
	    
	    model.addAttribute("overall_quantity",overall_quantity);

		return "MyCart";
		
	}
}