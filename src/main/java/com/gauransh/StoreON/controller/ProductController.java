package com.gauransh.StoreON.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gauransh.StoreON.entity.Comment_table;
import com.gauransh.StoreON.entity.Login_User;
import com.gauransh.StoreON.entity.Order_History;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.Comment_Repository;
import com.gauransh.StoreON.repository.Order_History_extractionRepository;
import com.gauransh.StoreON.repository.ProductDetailsRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	@Autowired
	private ProductDetailsRepository productdetailsrepository;
	
	@Autowired
	private Comment_Repository object;
	
	@Autowired
	private Order_History_extractionRepository Listofproduct;
	
	@GetMapping("/product")
	public String getproduct(Model model, HttpSession session,@RequestParam Integer productId) {

		String loggedInUser =(String) session.getAttribute("loggedInUser");
		model.addAttribute("loggedInUser",loggedInUser);
		if(loggedInUser==null) {
			return"redirect:/login_page.html"; 
		}
		
		Login_User user = (Login_User)	session.getAttribute("user");
		
		Integer userid = (Integer) user.getUserId();
		
		

		ProductDetails product = productdetailsrepository.findByProductId(productId);
		
		model.addAttribute("product",product);
		
		System.out.println("loggedInUser"+loggedInUser);
		
		product.setHighlights(					// added to get info in different lines
				product.getHighlights().replace("\n","<br>")
				);
		
		List<Comment_table> comments = object.findByproductid(productId);
		
		System.out.println("step 1");

		List<Order_History> ProductsinCart = Listofproduct.findByuserid(userid);
		
		int x;
		x=0;
		
		for(Order_History products : ProductsinCart) {
			
			if(products.getProductid().equals(productId)) {
			    x=1;
			}
		
		}
		model.addAttribute("x",x);
		
		model.addAttribute("comments",comments);
	


			return "product";
			
			
			
	}
}