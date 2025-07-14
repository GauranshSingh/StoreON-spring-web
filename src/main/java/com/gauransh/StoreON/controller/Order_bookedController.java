package com.gauransh.StoreON.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.gauransh.StoreON.entity.Cart;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.Empty_Cart;
import com.gauransh.StoreON.repository.MyCartRepository;
import com.gauransh.StoreON.repository.Product_details_ListRepository;
import com.gauransh.StoreON.repository.Quantity_productRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class Order_bookedController {

	@Autowired
	private MyCartRepository productdetailsrepository;
	
	@Autowired 
	private Product_details_ListRepository ProductDetailsCart;
	
	@Autowired 
	private Empty_Cart Product_deletion;
	
	@Autowired
	private Quantity_productRepository quantityRepo;
	
	@Transactional
	@PostMapping("/Order")
	public String getproduct(Model model, HttpSession session) {
		
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		if(loggedInUser==null) {
			return "redirect:/login_page.html";
		}
		model.addAttribute("loggedInUser",loggedInUser);
		

		Integer UserId=(Integer) session.getAttribute("user_id");
		List<Cart> ProductsinCart = productdetailsrepository.findByUserId(UserId);
		
	
		List<Integer> productIds = ProductsinCart.stream().map(Cart::getProductId).toList();

		
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
	    	 int availablity = c.getQuantity();
	    	 
	    	 availablity = availablity-(quantity);
	    	 
	    	 quantityRepo.Product_quantity_check(availablity,productId);
	    }
	    
	    model.addAttribute("cartProducts", Cart_Product_Details);
	      
	    model.addAttribute("quantityMap", quantityMap);  
			    
	    model.addAttribute("overall_price",overall_price);
	   	    
	    model.addAttribute("overall_quantity",overall_quantity);
	    
	    Product_deletion.EmptyCart(UserId);

		return "Checkout";
	}
}