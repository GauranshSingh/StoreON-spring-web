package com.gauransh.StoreON.repository;

import com.gauransh.StoreON.entity.Signup_User;
import org.springframework.data.jpa.repository.JpaRepository;  // to get teh jpa repository 				//
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

public interface Signup_UserRepository extends JpaRepository<Signup_User,String>{
	
	@Modifying				//to modify the query
	@Transactional				
	@Query(value = "INSERT INTO user_table(first_name, last_name,email,address,ph_number,password,role) " +
	               "VALUES (:first_name, :last_name, :email,:address,:ph_number,:password,:role)", nativeQuery = true)		//this shows that the queries are raw(belong to sql) and nor jqpl(java persistenace query language)
	int signupUser(@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("email") String email, @Param("ph_number") String ph_number,@Param("address") String address,@Param("password") String password, @Param("role") String role);
}



//@Param is used since this si for sql thus we don't use @ReqParam