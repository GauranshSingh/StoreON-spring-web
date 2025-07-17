package com.gauransh.StoreON.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gauransh.StoreON.entity.Order_History;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.Empty_Cart;
import com.gauransh.StoreON.repository.Order_History_extractionRepository;
import com.gauransh.StoreON.repository.Product_details_ListRepository;
import com.gauransh.StoreON.repository.Quantity_productRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class Order_HistoryController {
	
	@Autowired 
	private Product_details_ListRepository ProductDetailsCart;
	
	@Autowired 
	private Empty_Cart Product_deletion;
	
	@Autowired
	private Quantity_productRepository quantityRepo;
	
	@Autowired
	private Order_History_extractionRepository Listofproduct;
	
	
	
	@Transactional
	@GetMapping("/Order_History")
	public String getproduct(Model model, HttpSession session) {
		
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		if(loggedInUser==null) {
			return "redirect:/login_page.html";
		}
		model.addAttribute("loggedInUser",loggedInUser);
		

		Integer UserId=(Integer) session.getAttribute("user_id");
		
		
		List<Order_History> ProductsinCart = Listofproduct.findByuserid(UserId);
		
	
		List<Integer> productIds = ProductsinCart.stream().map(Order_History::getProductid).toList();		// to get teh product id

		
		List<ProductDetails> Cart_Product_Details = ProductDetailsCart.findByProductIdIn(productIds);
		
		
	    Map<Integer, Integer> quantityMap = new HashMap<>();

	    for (Order_History c : ProductsinCart) {
	        int productId = c.getProductid();
	        quantityMap.put(productId, quantityMap.getOrDefault(productId, 0) + 1);
	    }

	    double overall_price =0;
	    int overall_quantity = 0;
	    
	    for(ProductDetails c : Cart_Product_Details) {
	    	 int productId = c.getProductId();
	    	 int quantity = quantityMap.getOrDefault(productId, 0); // get quantity from map 
	    	 overall_quantity=overall_quantity+quantity;
	    	 overall_price = overall_price + c.getNewPrice()*quantity;
	    	 int availablity = c.getQuantity();
	    	 
	    	 availablity = availablity-(quantity);
	    	 
	    	 quantityRepo.Product_quantity_check(availablity,productId);
	    }
	    
	    model.addAttribute("cartProducts", Cart_Product_Details);
	      
	    model.addAttribute("quantityMap", quantityMap);  
			    
	    model.addAttribute("overall_price",overall_price);
	   	    
	    model.addAttribute("overall_quantity",overall_quantity);
	    
	    Product_deletion.EmptyCart(UserId);

		return "Order_History";
	}
}