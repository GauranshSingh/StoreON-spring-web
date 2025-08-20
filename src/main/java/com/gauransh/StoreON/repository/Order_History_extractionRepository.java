package com.gauransh.StoreON.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Order_History;

public interface Order_History_extractionRepository extends JpaRepository<Order_History,Integer>{
	List<Order_History> findByuserid(@Param("Userid") Integer Userid);
}
