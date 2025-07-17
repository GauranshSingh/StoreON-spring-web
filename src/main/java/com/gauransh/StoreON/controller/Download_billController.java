package com.gauransh.StoreON.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gauransh.StoreON.entity.ProductDetails;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class Download_billController {

	
	
	@GetMapping("/download-bill")
	public void downloadBill(HttpServletResponse response,
	                         @RequestParam("name") String name,
	                         @RequestParam("total") double total,
	                         @RequestParam("quantity") int quantity,
	                         @RequestParam("orderId") String orderId,
	                         HttpSession session) throws Exception {

	    List<ProductDetails> cartProducts = (List<ProductDetails>) session.getAttribute("cartProducts");
	    
	    Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");

	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=StoreON_bill.pdf");

	    Document document = new Document();
	    PdfWriter.getInstance(document, response.getOutputStream());
	    document.open();

	    document.add(new Paragraph("StoreON - Order Bill"));
	    document.add(new Paragraph("Customer Name: " + name));
	    document.add(new Paragraph("Order ID: " + orderId));
	    document.add(new Paragraph("Expected Delivery: Within 3 days"));
	    document.add(new Paragraph(" "));

	    PdfPTable table = new PdfPTable(5);
	    table.addCell("Product");
	    table.addCell("Name");
	    table.addCell("Rating");
	    table.addCell("Price");
	    table.addCell("Quantity");

	    for (ProductDetails product : cartProducts) {
	        table.addCell(""); // You can skip image for now in PDF
	        table.addCell(product.getName());
	        table.addCell(product.getRating());
	        table.addCell(String.valueOf(product.getNewPrice()));
	        table.addCell(String.valueOf(quantityMap.get(product.getProductId())));
	    }

	    document.add(table);
	    document.add(new Paragraph("Total Quantity: " + quantity));
	    document.add(new Paragraph("Total Amount: $" + total));
	    document.close();
	}

	
}
