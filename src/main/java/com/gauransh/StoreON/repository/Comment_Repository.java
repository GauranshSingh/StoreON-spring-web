package com.gauransh.StoreON.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Comment_table;

import jakarta.transaction.Transactional;

public interface Comment_Repository extends JpaRepository<Comment_table,String>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Comment_table(user_id,product_id,comment,first_name,last_name)"
			+ " VALUES (:userid,:productid,:comment,:first_name,:last_name)", nativeQuery = true)
	void add_comment(@Param("userid") Integer userid, @Param("productid") Integer productid, @Param("comment") String comment,@Param("first_name") String first_name,@Param("last_name") String last_name);
	
	 List<Comment_table> findByproductid(@Param("productid") Integer productid);
	 
}
