package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Cart;

import jakarta.transaction.Transactional;

public interface Delete_from_CartRepository extends JpaRepository<Cart,Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "DELETE  FROM cart WHERE cart_id=(SELECT cart_id FROM cart WHERE user_id=:userId AND product_id = :ProductId LIMIT 1)"
	, nativeQuery = true)
	void delete_product_fromcart(@Param("ProductId") Integer ProductId, @Param("userId") Integer userId);
	
}
