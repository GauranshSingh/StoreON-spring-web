package com.gauransh.StoreON.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauransh.StoreON.entity.ProductDetails;

public interface Product_details_ListRepository extends JpaRepository<ProductDetails, Integer>{
	List<ProductDetails> findByProductIdIn(List<Integer> productId );
	ProductDetails findByProductId(Integer productId);
}
