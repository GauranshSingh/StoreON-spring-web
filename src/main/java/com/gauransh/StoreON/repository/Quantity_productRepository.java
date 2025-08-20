package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.ProductDetails;

import jakarta.transaction.Transactional;

public interface Quantity_productRepository extends JpaRepository<ProductDetails,Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product_details "
            + "SET quantity = :quantity "
            + "WHERE product_id = :product_id", nativeQuery = true)
	void Product_quantity_check(@Param("quantity") Integer quantity, @Param("product_id") Integer product_id);
}
