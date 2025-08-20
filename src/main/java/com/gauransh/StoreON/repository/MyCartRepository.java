package com.gauransh.StoreON.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauransh.StoreON.entity.Cart;


public interface MyCartRepository extends JpaRepository<Cart, Integer>{
    List<Cart> findByUserId(Integer Userid);
}
