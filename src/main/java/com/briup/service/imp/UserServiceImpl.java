package com.briup.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.dao.RoleDao;
import com.briup.dao.UserDao;
import com.briup.service.IUserService;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月26日 下午4:51:21 
* 类说明 
*/
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		User user = userDao.findUserByName(name);
		return user;
	}

	@Override
	public Page<User> findAllUsers() {
		// TODO Auto-generated method stub
		return findAllUsers(0);
	}

	@Override
	public Page<User> findAllUsers(Integer pageIndex) {
		return userDao.findAll(PageRequest.of(pageIndex,3));
	}
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getOne(id);
	}

	@Override
	public void savaOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	@Override
	public Page<User> findUser(Integer roleId,Integer pageIndex) {
		// TODO Auto-generated method stub
		//Role role = roleDao.getOne(status);
		Role role = roleDao.findById(roleId).get();
		Page<User> users = userDao.findByRole(role, PageRequest.of(pageIndex,3));
		return users;
	}

	@Override
	public List<User> findUserByRole(Integer roleId) {
		Role role = roleDao.findById(roleId).get();
		List<User> roles = userDao.findByRole(role);
		return roles;
	}
}
