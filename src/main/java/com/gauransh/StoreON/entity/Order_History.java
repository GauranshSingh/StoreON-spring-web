package com.gauransh.StoreON.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="order_history")
public class Order_History {

	@Column(name = "user_id")
	private Integer userid;
	
	@Column(name = "product_id")
	private Integer Productid;
	
	@Id
	@Column(name = "order_id")
	private Integer Orderid;
	
	
	public Integer getuserid() {
		return userid;
	}
	
	public void setuserid(Integer userid){
		this.userid = userid;
	}
	
	public Integer getProductid() {
		return Productid;
	}
	
	public void setProductid(Integer Productid){
		this.Productid = Productid;
	}
	
	public Integer getOrderid() {
		return Orderid;
	}
	
	public void setOrderid(Integer Orderid){
		this.Orderid = Orderid;
	}
	
}
