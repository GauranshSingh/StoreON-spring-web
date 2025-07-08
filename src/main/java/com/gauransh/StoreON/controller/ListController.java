//
package com.gauransh.StoreON.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gauransh.StoreON.entity.list_2;
import com.gauransh.StoreON.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
@Controller
public class ListController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/List")
	public String getdata(HttpSession session, Model model){
		List<list_2> products = productRepository.findAll();
	    model.addAttribute("products", products);
		String loggedInUser =(String) session.getAttribute("loggedInUser");
		
		if(loggedInUser==null) {
			return "redirect:/login_page.html";
		}
		
		
		model.addAttribute("loggedInUser",loggedInUser);
		return "List";
			}
}
