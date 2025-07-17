package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Order_History;

import jakarta.transaction.Transactional;

public interface Order_HistoryRepository extends JpaRepository<Order_History,Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO order_history(user_id,product_id)"
			+ "VALUES(:Userid,:Productid)",nativeQuery = true)
	void add_into_order(@Param("Userid") Integer Userid , @Param("Productid") Integer Productid);
	
}
