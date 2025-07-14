package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.gauransh.StoreON.entity.Cart;
import jakarta.transaction.Transactional;

public interface Empty_Cart extends JpaRepository<Cart,Integer>{

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart WHERE user_id = :userId", nativeQuery = true)
	
	void EmptyCart(Integer userId);
}