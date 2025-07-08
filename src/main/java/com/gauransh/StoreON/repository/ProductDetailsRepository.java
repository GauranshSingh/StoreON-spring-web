package com.gauransh.StoreON.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauransh.StoreON.entity.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer>{
	ProductDetails findByProductId(Integer productId);
}
