package com.gauransh.StoreON.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gauransh.StoreON.entity.list_2;

public interface ProductRepository extends JpaRepository<list_2,Integer> {
//		List<Product> getProduct();

}