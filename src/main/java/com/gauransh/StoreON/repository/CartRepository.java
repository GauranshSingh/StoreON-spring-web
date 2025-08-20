package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.ProductDetails;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<ProductDetails, Integer>{
	
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart(user_id, product_id) VALUES (:user_id, :productId)", nativeQuery = true)
    void AddintoCart(@Param("user_id") Integer user_id, @Param("productId") Integer productID);
}
