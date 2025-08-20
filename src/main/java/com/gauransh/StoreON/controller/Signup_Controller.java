package com.gauransh.StoreON.controller;

import org.springframework.beans.factory.annotation.Autowired;      // to get auto wired function access
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.gauransh.Signin_STOREON.entity.User;
import com.gauransh.StoreON.repository.Signup_UserRepository;				// to get user repository function access

@Controller			// to handle web request
public class Signup_Controller{

	@Autowired
	private Signup_UserRepository userRepository;
	  @PostMapping("/signup")					//this is the file that attached to my html loginpage from(post) it had form(l eg-    <form action="http://localhost:8090/login" method="post">
	  public String signup(@RequestParam String first_name, @RequestParam String last_name , @RequestParam String email, @RequestParam String ph_number, @RequestParam String address, @RequestParam String password){

		  String role="Costumer";
		  
	      int result =  userRepository.signupUser(first_name,last_name,email,ph_number,address,password,role);
	  	if (result==1) {
	          return "redirect:/login_page.html"; // start to log in again now
	  	}
	       else {
	          return "redirect:http://localhost:8091/Signup_STOREON.html";			 // Redirect back to Sign in page
	       }
	  }
}
