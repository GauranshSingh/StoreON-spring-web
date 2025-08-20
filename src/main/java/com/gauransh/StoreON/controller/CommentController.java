package com.gauransh.StoreON.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.Login_User;
import com.gauransh.StoreON.repository.Comment_Repository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	
	@Autowired
	private Comment_Repository object;

	
	@GetMapping("/comment")
	public String addCommment(HttpSession session, Model model,@RequestParam String comment,@RequestParam Integer productId) {
		
		Login_User user = (Login_User)	session.getAttribute("user");
		
		Integer userid = (Integer) user.getUserId();
		
		String firstname= (String) user.getFirstName();
				
		String lastname= (String) user.getLastName();
		
		object.add_comment(userid,productId,comment,firstname,lastname);
		
	    return "redirect:/product?productId=" + productId;
	}

}
