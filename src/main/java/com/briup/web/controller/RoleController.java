package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Role;
import com.briup.service.IRoleService;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月27日 上午11:50:51 
* 类说明 
*/
@Controller
public class RoleController {
	@Autowired
	private IRoleService service;
	@RequestMapping("toRole")
	public String toRole(HttpSession session,Integer num) {
		Page<Role> roles;
		//如果num为空说明是初始化页面的，不为空说明有翻页操作要保存当前页
		if(num!=null) {
			session.setAttribute("num", num);
		}
		//获取num
		Integer page = (Integer)session.getAttribute("num");
		if(page!=null) {
			//System.out.println(page);
			roles = service.findAllRoles(page-1);
			//解决删除最后一页数据后显示空白页的问题
			if(page>roles.getTotalPages()) {
				roles = service.findAllRoles(page-2);
			}
			session.setAttribute("roles", roles);
		}
		else {
			roles = service.findAllRoles();
			session.setAttribute("roles", roles);
		}
		List<Integer> list = service.findPage(roles.getSize());
		session.setAttribute("page", list);
		List<Role> roleNames = service.findAllRoleName();
		session.setAttribute("roleNames", roleNames);
		return "pages/role";
	}
	@RequestMapping("saveRole")
	@ResponseBody
	public String saveRole(Role role) {
		String msg;
		if(role.getId()==null) {
			msg= "添加成功";
		}
		else {
			msg= "修改成功";
		}
		service.savaOrUpdateRole(role);
		return msg;
	}
	@RequestMapping("deleteRole")
	@ResponseBody
	public String deleteRole(Integer id) {
		service.deleteRole(id);
		return "删除成功";
	}
	@RequestMapping("findRole")
	@ResponseBody
	public Role findRoleById(Integer id) {
		Role role = service.findRoleById(id);
		return role;
	}
}
