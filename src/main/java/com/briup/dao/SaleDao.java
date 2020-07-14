package com.briup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.bean.Chance;
import com.briup.bean.User;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年4月1日 下午3:07:36 
* 类说明 
*/
public interface SaleDao extends JpaRepository<Chance, Integer>{
	Page<Chance> findByCreator(User creator,Pageable pageRequest);
	@Transactional
	@Query("select ch from Chance ch where ch.creator=?1 and ch.customer=?2 and ch.address=?3")
	Page<Chance> findAllPage1(User user,String name,String address,Pageable pageable);
	
	@Transactional
	@Query("select ch from Chance ch where ch.creator=?1 and ch.address=?2")
	Page<Chance> findAllPage2(User user,String address,Pageable pageable);
	
	
	@Transactional
	@Query("select ch from Chance ch where ch.customer=?1 and ch.creator=?2")
	Page<Chance> findAllPage3(String name,User user,Pageable pageable);
}
