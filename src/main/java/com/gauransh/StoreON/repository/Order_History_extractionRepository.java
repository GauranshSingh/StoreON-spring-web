package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Order_History;
import java.util.List;

public interface Order_History_extractionRepository extends JpaRepository<Order_History,Integer>{
	List<Order_History> findByuserid(@Param("Userid") Integer Userid);
}
