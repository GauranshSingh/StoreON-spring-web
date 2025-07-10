package com.gauransh.StoreON.repository;







//### THIS CODE IS NOT WORKING right now !!!!!!!!!!!!!!!! ####







import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.ProductDetails;

import jakarta.transaction.Transactional;

public interface Add2CartRepository extends JpaRepository<ProductDetails,Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO cart(user_id,product_id) VALUES ( :userId, :productId)", nativeQuery=true)
	
	void add2cart(@Param("userId") Integer userId,@Param("productId") Integer productId);

}
