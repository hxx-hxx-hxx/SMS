package com.briup.web.controller;
/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月26日 下午3:30:20 
* 类说明 :用来映射thymeleaf页面的Controller类
*/

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.service.IRoleService;

@Controller
public class ViewController {
	
	@RequestMapping("login")
	public String toLoginPage() {
		//session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("index")
	public String toIndexPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user==null) {
			return "login";
		}
		else {
			return "index";
		}
	}
	@RequestMapping("quit")
	public String quit(HttpSession session) {
		Enumeration<String> names = session.getAttributeNames();
		while(names.hasMoreElements()){
			System.out.println(session.getAttributeNames().nextElement().toString());
			
			session.removeAttribute(names.nextElement().toString());
		}
		return "login";
	}
}
