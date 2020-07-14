package com.briup.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.Role;
import com.briup.bean.User;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月26日 下午4:47:48 
* 类说明 
*/
public interface IUserService {
	public User findUserByName(String name);
	Page<User> findAllUsers();
	Page<User> findAllUsers(Integer pageIndex);
	User findUserById(Integer id);
	void savaOrUpdateUser(User user);
	void deleteUser(Integer id);
	Page<User> findUser(Integer status,Integer pageIndex);
	List<User> findUserByRole(Integer roleId);
}
