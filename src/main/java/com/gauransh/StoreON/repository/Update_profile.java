package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Signup_User;

import jakarta.transaction.Transactional;

public interface Update_profile extends JpaRepository<Signup_User,Integer>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE user_table"
			+ " SET first_name = :first_name, last_name = :last_name, ph_number = :ph_number, address = :address, password = :password, email = :email "
			+ " WHERE user_id = :user_id", nativeQuery = true)
	void Update_profile_funciton(@Param("user_id") Integer user_ID,@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("email") String email, @Param("ph_number") String ph_number,@Param("address") String address,@Param("password") String password);
}