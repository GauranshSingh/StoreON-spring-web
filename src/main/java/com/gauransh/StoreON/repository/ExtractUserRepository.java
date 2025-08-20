package com.gauransh.StoreON.repository;
    
import org.springframework.data.jpa.repository.JpaRepository;

import com.gauransh.StoreON.entity.ExtractUser;

public interface ExtractUserRepository extends JpaRepository<ExtractUser, String> {
//	List<ExtractUser> findAll();		// how doesthis funtion work without declaration in repository
}