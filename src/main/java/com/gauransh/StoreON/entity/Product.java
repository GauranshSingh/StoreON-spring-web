package com.gauransh.StoreON.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="list")
public class Product {
	@Id
	@Column(name="name")
	private String Name;
	
	@Column(name="category")
	private String Category;
	
	@Column(name="image")
	private String Image;
	
	@Column(name="price")
	private BigDecimal Price;
	
	public void setName(String Name) {
		this.Name=Name;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setCategory(String Category) {
		this.Category=Category;
	}
	
	public String getCategory() {
		return Category;
	}
	
	public void setImage(String Image) {
		this.Image=Image;
	}
	
	public String getImage() {
		return Image;
	}
	
	public void setPrice(BigDecimal Price){
		this.Price=Price;
	}
	
	public BigDecimal getPrice() {
		return Price;
	}
}