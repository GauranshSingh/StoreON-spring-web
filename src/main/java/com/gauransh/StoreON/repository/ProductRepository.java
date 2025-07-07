package com.gauransh.StoreON.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.gauransh.StoreON.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
//		List<Product> findAll();

}