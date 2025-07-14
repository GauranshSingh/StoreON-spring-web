package com.gauransh.StoreON.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gauransh.StoreON.entity.list_2;


public interface ProductRepository extends JpaRepository<list_2,Integer> {
	
	
//	@Modifying     to be used only in delete update or add not in select
//	@Transactional
	@Query(value = "SELECT * FROM list"
			+ " WHERE category = :Category", nativeQuery = true)
	List<list_2> getByCategory(String Category);
	
}