package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Chance;
import com.briup.bean.User;
import com.briup.service.ISaleService;
import com.briup.service.IUserService;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年4月1日 下午3:03:47 
* 类说明 
*/
@Controller
public class SaleController {
	@Autowired
	ISaleService saleService;
	@Autowired
	IUserService userService;
	@RequestMapping("toSale")
	public String toSale(HttpSession session) {
		Page<Chance> chances = null;
		User user = (User)session.getAttribute("user");
		
		String name2 = (String)session.getAttribute("name");
		String address2 = (String)session.getAttribute("address");
		if(name2==null && address2==null) {
			chances=saleService.findByUser(user, 0);
		}if(name2!=null && address2==null) {
			chances = saleService.findAllChances(name2, user, 0);
		}if(name2!=null && address2!=null) {
			chances = saleService.findAllChances(user, name2, address2, 0);	
		}if(name2==null && address2!=null) {
			chances = saleService.findAllChances(user, address2, 0);
		}
		List<User> jlUsers = userService.findUserByRole(4);
		session.setAttribute("jlUsers", jlUsers);
		session.setAttribute("chances", chances);
		return "pages/sales";
	}
	//点击翻页
	@RequestMapping("PagetoSale")
	public String PagetoSale(HttpSession session,Integer pageIndex,String name,String address) {
		//如果address或者name不为空，那么说明是点击了查询
		Page<Chance> chances = null;
		
		//System.out.println(name+"aa----"+address);
		User user = (User)session.getAttribute("user");
		if("null".equals(name)) {
			name=null;
		}
		if("null".equals(address)) {
			address=null;
		}
		if((name!=null||address!=null) && pageIndex==null) {
			session.setAttribute("name", name);
			session.setAttribute("address", address);
		}
		//说明点击了翻页，判断是什么情况下的翻页
		else if((name==null && address==null) && pageIndex!=null) {
			String name2 = (String)session.getAttribute("name");
			String address2 = (String)session.getAttribute("address");
			//说明是普通情况下的翻页
			if((name2 == null && address2 == null) && pageIndex!=null) {
				chances = saleService.findByUser(user, pageIndex);
			}else if((name2!=null && address2==null) && pageIndex!=null) {
				chances = saleService.findAllChances(name2, user, pageIndex);
			}else if((name2==null && address2!=null) && pageIndex!=null) {
				chances = saleService.findAllChances(user, address2, pageIndex);
			}else if(name2!=null && address2!=null && pageIndex!=null){
				chances = saleService.findAllChances(user, name2, address2, pageIndex);
			}
			session.setAttribute("chances", chances);
		}
		//Page<Chance> chances = saleService.findByUser((User)session.getAttribute("user"), pageIndex);
		
		return "pages/sales";
	}
	@RequestMapping("SaveChance")
	@ResponseBody
	public String toSave(Chance chance) {
		String msg;
		if(chance.getId()==null) {
			msg= "添加成功";
		}
		else {
			msg= "修改成功";
		}
		saleService.savaOrUpdateChance(chance);
		return msg;
	}
	@RequestMapping("findChance")
	@ResponseBody
	public Chance findChance(Integer id) {
		Chance chance = saleService.findById(id);
		return chance;
	} 
	@RequestMapping("deleteChance")
	@ResponseBody
	public String deleteChance(Integer id) {
		saleService.deleteChance(id);
		return "删除成功";
	} 
}
