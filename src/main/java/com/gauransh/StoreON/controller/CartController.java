package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.ProductDetailsRepository;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private ProductDetailsRepository productdetailsrepository;
	
	@GetMapping("/cart")
	public String getproduct(Model model, HttpSession session) {
		List<ProductDetails> cartDetails = productdetailsrepository.findAll();
		
		//on button click - cartDetails.add(product)
		//session.setAttribute("cart",cartDetails);
		
		
//		model.addAttribute("productdetails",productdetails);
//		String loggedInUser =(String) session.getAttribute("loggedInUser");
//		model.addAttribute("loggedInUser",loggedInUser);
//		if(loggedInUser==null) {
//			return"redirect:/login_page.html";
//		}
		return "product";
	}
}
