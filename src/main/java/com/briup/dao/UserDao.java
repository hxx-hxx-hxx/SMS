package com.briup.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.bean.Role;
import com.briup.bean.User;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月26日 下午4:56:12 
* 类说明 
*/
public interface UserDao extends JpaRepository<User,Integer>{
	User findUserByName(String name); 
	Page<User> findByRole(Role role, Pageable pageRequest);
	List<User> findByRole(Role role);
	
	//@Query("delete u from User u where u.id=?1")
	//void delete(Integer id);
	//Page<User> findAll(Integer id,Pageable pageable);
}
