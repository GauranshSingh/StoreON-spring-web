//
package com.gauransh.StoreON.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.list_2;
import com.gauransh.StoreON.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
@Controller
public class ListController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/List")
	public String getdata(HttpSession session, Model model, @RequestParam String category){
		System.out.println("step: 1");

		String loggedInUser =(String) session.getAttribute("loggedInUser");
		
		if(loggedInUser==null) {
			return "redirect:/login_page.html";
		}
		model.addAttribute("loggedInUser",loggedInUser);
		
		System.out.println("step: 2");

		
		List<list_2> products = productRepository.getByCategory(category);
	    model.addAttribute("products", products);
	    
		System.out.println("step: 3");

	    
		
		return "List";
			}
}
