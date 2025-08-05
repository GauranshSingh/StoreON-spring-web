package com.gauransh.StoreON.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gauransh.StoreON.entity.Comment_table;

public interface List_userid_commenttableRepository extends JpaRepository<Comment_table,String>{
	List<Integer> findBycommentIn(@Param("comments") List<Comment_table> comments);
	}
 