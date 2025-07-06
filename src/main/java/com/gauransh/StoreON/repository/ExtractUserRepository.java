package com.gauransh.StoreON.repository;
    
import com.gauransh.StoreON.entity.ExtractUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractUserRepository extends JpaRepository<ExtractUser, String> {
//	List<ExtractUser> findAll();		// how doesthis funtion work without declaration in repository
}