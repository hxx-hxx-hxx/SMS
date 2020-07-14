package com.briup.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.dao.RoleDao;
import com.briup.service.IRoleService;

/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月27日 下午2:42:42 
* 类说明 
*/
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleDao roleDao;
	@Override
	public Page<Role> findAllRoles() {
		return findAllRoles(0);
	}

	@Override
	public Page<Role> findAllRoles(Integer pageIndex) {
		Page<Role> page = roleDao.findAll(PageRequest.of(pageIndex,2));
		return page;
	}

	@Override
	public Role findRoleById(Integer id) {
		Role role = roleDao.getOne(id);
		return role;
	}

	@Override
	public void savaOrUpdateRole(Role role) {
		roleDao.save(role);
	}

	@Override
	public void deleteRole(Integer id) {
		roleDao.deleteById(id);
	}

	@Override
	public List<Integer> findPage(Integer num) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int size = roleDao.findAll().size();
		int ceil = (int)(Math.ceil(size/((double)num)));
		for(int i=1;i<=ceil;i++) {
			list.add(i);
		}
		return list;
	}

	@Override
	public List<Role> findAllRoleName() {
		// TODO Auto-generated method stub
		List<Role> list = roleDao.findAll();
		return list;
	}

}
