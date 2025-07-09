package com.gauransh.StoreON.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@Column(name = "cart_id")
	private Integer CartId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "product_id")
	private Integer ProductId;
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setProductId(Integer ProductId) {
		this.ProductId = ProductId;
	}
	
	public Integer getProductId() {
		return ProductId;
	}
	
	public void setCartId(Integer CartId) {
		this.CartId = CartId;
	}
	
	public Integer getCartId() {
		return CartId;
	}
}
