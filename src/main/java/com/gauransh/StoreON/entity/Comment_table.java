package com.gauransh.StoreON.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment_table")
public class Comment_table {

	@Column(name = "user_id")
	private Integer userid;
	

	@Column(name = "product_id")
	private Integer productid;
	
	@Id
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "first_name")
	private String firstname;
	
	@Column(name = "last_name")
	private String lastname;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	public Integer getuserid() {
		return userid;
	}
	
	public void setuserid(Integer userid) {
		this.userid = userid;
	}
	
	public Integer getproductid() {
		return productid;
	}
	
	public void setproductid(Integer productid) {
		this.productid = productid;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
