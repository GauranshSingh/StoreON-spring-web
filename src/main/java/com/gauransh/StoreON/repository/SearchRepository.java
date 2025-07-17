package com.gauransh.StoreON.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gauransh.StoreON.entity.ProductDetails;
import jakarta.transaction.Transactional;

public interface SearchRepository extends JpaRepository<ProductDetails,Integer>{
	
	@Transactional
	@Query(value = "SELECT * FROM public.product_details WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
	List<ProductDetails> findByName(@Param("name") String name);
}