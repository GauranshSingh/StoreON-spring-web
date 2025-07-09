package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauransh.StoreON.entity.Cart;
import java.util.List;


public interface MyCartRepository extends JpaRepository<Cart, Integer>{
    List<Cart> findByUserId(Integer userId);
    
}
