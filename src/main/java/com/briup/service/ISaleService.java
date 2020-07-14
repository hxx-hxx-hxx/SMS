package com.briup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.briup.bean.Chance;
import com.briup.bean.User;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年4月1日 下午3:09:04 
* 类说明 
*/
public interface ISaleService {
	Page<Chance> findByUser(User user,Integer pageIndex);
	Page<Chance> findAllChances(User user,String name,String address,Integer pageIndex);
	Page<Chance> findAllChances(User user,String address,Integer pageIndex);
	Page<Chance> findAllChances(String name,User user,Integer pageIndex);
	void savaOrUpdateChance(Chance chance);
	Chance findById(Integer id);
	void deleteChance(Integer id);
}
