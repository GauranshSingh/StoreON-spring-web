package com.gauransh.StoreON.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.SearchRepository;
import com.gauransh.StoreON.service.WordVectorService;
import jakarta.servlet.http.HttpSession;

@Controller
public class Search_bar_Controller {
	
	@Autowired
	private SearchRepository search_product;
	
	@Autowired
	private WordVectorService wordVectorService;

	public record ProductMatch(ProductDetails product, boolean isAiMatch) {}

	@GetMapping("/Search")
	public String findproduct(HttpSession session, Model model, @RequestParam String name) {
	    String loggedInUser = (String) session.getAttribute("loggedInUser");
	    model.addAttribute("loggedInUser", loggedInUser);
	    if (loggedInUser == null) return "redirect:/login_page.html";
	    
	    List<String> similarWords = wordVectorService.getSimilarWords(name);

	    List<ProductMatch> finalResults = new ArrayList<>();

	    Set<Long> seenProductIds = new HashSet<>();

	 for (ProductDetails product : search_product.findByName(name)) {	// this is decleration of the product to be ai suggested or not
	     finalResults.add(new ProductMatch(product, false));
	     seenProductIds.add(Long.valueOf(product.getProductId())); 
	 }

	 // these are the words that are found by AI and 
	 for (String word : similarWords) {
	     for (ProductDetails product : search_product.findByName(word)) {
	         if (!seenProductIds.contains(Long.valueOf(product.getProductId()))) {
	             finalResults.add(new ProductMatch(product, true));
	             seenProductIds.add(Long.valueOf(product.getProductId()));
	         }
	     }
	 }
	    model.addAttribute("productMatches", finalResults);
	    return "Search_result";
	}

	@GetMapping("/suggestions")
	@ResponseBody
	public List<String> getSuggestions(@RequestParam String keyword) {
	    return wordVectorService.getSimilarWords(keyword);  //already implemented by me
	}
}