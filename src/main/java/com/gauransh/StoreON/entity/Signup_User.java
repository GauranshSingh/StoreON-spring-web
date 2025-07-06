package com.gauransh.StoreON.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")

public class Signup_User{
	@Id
	@Column(name="user_id")
	private String user_id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "ph_number")
	private String ph_number;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "Email")
	private String Email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;

	public String getUser_id() {     // we will get first then set 
	return user_id;
	}
	
	public void setuser_id(String user_id) {
	this.user_id=user_id;
	}
	
	public String getfirst_name() {
		return first_name;
	}
	
	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setlast_name(String last_name) {
		this.last_name=last_name;
	}
	
	public String getph_number() {
		return ph_number;
	}
	
	public void setph_number(String ph_number) {
		this.ph_number = ph_number;
	}
	
	public String get_address() {
		return address;
	}
	
	public void set_address(String address) {
		this.address= address;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getpassword() {
		return password;
	}
	
	public void setpassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
	    this.role = "customer"; 
	}

	public String getRole() {
	    return role;
	}
}