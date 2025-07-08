package com.gauransh.StoreON.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="list")
public class list_2{
	
	@Id
	
	@Column(name="id")
	private Integer productId;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="category")
	private String Category;
	
	@Column(name="image")
	private String Image;
	
	@Column(name="price")
	private BigDecimal Price;
	
	public void setProductId(String Name) {
		this.Name=Name;
	}
	
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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