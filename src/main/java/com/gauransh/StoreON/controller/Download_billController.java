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
	public void downloadBill(HttpServletResponse response, @RequestParam("name") String name, @RequestParam("last_name") String last_name, @RequestParam("total") double total, @RequestParam("quantity") int quantity, @RequestParam("orderId") String orderId, HttpSession session) throws Exception {

		
	    List<ProductDetails> cartProducts = (List<ProductDetails>) session.getAttribute("cartProducts");
	    Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");
//	    Double overallPrice = (Double) session.getAttribute("overall_price");
//	    Integer overallQuantity = (Integer) session.getAttribute("overall_quantity");

	    
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=StoreON_bill.pdf");

	    // Content-Disposition", "attachment it says download the file.
	    Document document = new Document();
	    PdfWriter.getInstance(document, response.getOutputStream());
	    document.open();
	    
	    document.add(new Paragraph("StoreON - Order Bill"));
	    document.add(new Paragraph("Customer Name: " +name+ " " +last_name));
	    document.add(new Paragraph("Order ID:  78382(S)"));
	    document.add(new Paragraph("Expected Delivery: Within 3 days"));
	    document.add(new Paragraph(" "));
    
	    PdfPTable table = new PdfPTable(5);
	    table.addCell("Sr.No.");
	    table.addCell("Name");
	    table.addCell("Product category");
	    table.addCell("Price");
	    table.addCell("Quantity");
	    
	    int i=0;

	    for (ProductDetails product : cartProducts) {
	    	i++;
	        if (product == null) continue;
	        table.addCell(String.valueOf(i));
	        table.addCell(product.getName());
	        table.addCell(product.getcategory());
	        table.addCell(String.valueOf(product.getNewPrice()));
	        table.addCell(String.valueOf(quantityMap.getOrDefault(product.getProductId(), 1)));
	    }


	    document.add(table);
	    document.add(new Paragraph("Total Quantity: " + quantity));
	    document.add(new Paragraph("Total Amount: $" + total));
	    document.close();

	    
	    //to remove the product from cart on order complete
	    session.removeAttribute("cartProducts");
	    session.removeAttribute("quantityMap");
	    session.removeAttribute("overall_price");
	    session.removeAttribute("overall_quantity");


	}
	
}
