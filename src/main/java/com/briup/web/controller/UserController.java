package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.service.IRoleService;
import com.briup.service.IUserService;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月26日 下午4:33:45 
* 类说明 
*/
@Controller
public class UserController {
	@Autowired
	private IUserService userservice;
	@Autowired
	private IRoleService roleService;
	
	//获取前台输入的用户名密码进行校验
	@RequestMapping("user/login")
	@ResponseBody
	public String login(String name,String password,HttpSession session) {
		User user;
		user = userservice.findUserByName(name);
		if(user==null) {
			return "当前用户不存在";
		}
		else if(!user.getPassword().equals(password)) {
			return "密码错误";
		}
		else if(user.getFlag()!=1) {
			return "该用户已被注销";
		}
		else {
			session.setAttribute("user", user);
			return "success";
		}
	}
	@RequestMapping("toUser")
	public String toUser(HttpSession session) {
		Page<User> users;
		Integer roleId = (Integer)session.getAttribute("status");
		if(roleId==null)
			users = userservice.findAllUsers();
		else {
			users = userservice.findUser(roleId, 0);
		}
		session.setAttribute("users", users);
		List<Role> roleNames = roleService.findAllRoleName();
		session.setAttribute("roleNames", roleNames);
		return "pages/user";
	}
	@RequestMapping("pageToUser")
	public String pageToUser(HttpSession session,Integer num,Integer status) {
		Page<User> userForId =null;
		//如果status不为null并且num==null,那么说明点击了条件查询,session里面的status要更新
		if(status!=null && num==null) {
			session.setAttribute("status", status);
		}
		//说明点击了翻页
		else if(status==null && num!=null){
			Integer RoleId = (Integer) session.getAttribute("status");
			//判断是什么情况下的
			if(RoleId==null) {
				userForId = userservice.findAllUsers(num);
			}
			else {
				userForId = userservice.findUser(RoleId, num);
			}
			session.setAttribute("users", userForId);
		}	
		return "pages/user";
	}
	@RequestMapping("saveUser")
	@ResponseBody
	public String saveUser(User user2) {
		String msg;
		
		if(user2.getId()==null) {
			msg= "添加成功";
		}
		else {
			msg= "修改成功";
		}
		userservice.savaOrUpdateUser(user2);
		return msg;
	}
	@RequestMapping("deleteUser")
	@ResponseBody
	public String deleteUser(Integer id) {
		userservice.deleteUser(id);
		return "删除成功";
	}
	@RequestMapping("findUser")
	@ResponseBody
	public User findRoleById(Integer id) {
		User user3 = userservice.findUserById(id);
		return user3;
	}
	@RequestMapping("resetUser")
	@ResponseBody
	public String resetUser(HttpSession session) {
		session.removeAttribute("status");
		return "重置成功";
	}
}
