package com.briup.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.briup.bean.Chance;
import com.briup.bean.User;
import com.briup.dao.SaleDao;
import com.briup.service.ISaleService;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年4月1日 下午3:37:17 
* 类说明 
*/
@Service
public class SaleServiceImpl implements ISaleService{
	@Autowired
	SaleDao saleDao;

	@Override
	public void savaOrUpdateChance(Chance chance) {
		saleDao.save(chance);
	}

	@Override
	public Chance findById(Integer id) {
		// TODO Auto-generated method stub
		Chance chance = saleDao.findById(id).get();
		return chance;
	}

	@Override
	public Page<Chance> findByUser(User user, Integer pageIndex) {
		// TODO Auto-generated method stub
		Page<Chance> chance = saleDao.findByCreator(user, PageRequest.of(pageIndex, 3));
		return chance;
	}

	@Override
	public void deleteChance(Integer id) {
		saleDao.deleteById(id);	
	}

	@Override
	public Page<Chance> findAllChances(User user, String name, String address, Integer pageIndex) {
		// TODO Auto-generated method stub
		Page<Chance> chance = saleDao.findAllPage1(user, name, address, PageRequest.of(pageIndex, 3));
		return chance;
	}
	
	@Override
	public Page<Chance> findAllChances(User user, String address, Integer pageIndex) {
		// TODO Auto-generated method stub
		Page<Chance> chance = saleDao.findAllPage2(user,address, PageRequest.of(pageIndex, 3));
		return chance;
	}

	@Override
	public Page<Chance> findAllChances(String name, User user, Integer pageIndex) {
		Page<Chance> chance = saleDao.findAllPage3(name,user, PageRequest.of(pageIndex, 3));
		System.out.println("名字呗调用了");
		return chance;
	}

}
