package com.gauransh.StoreON.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Login_User;

public interface Signup_User_IntegerRepository extends JpaRepository<Login_User,Integer>{

	List<Login_User> findByUserIdIn(@Param ("userId") List<Integer> userId);
		
}
