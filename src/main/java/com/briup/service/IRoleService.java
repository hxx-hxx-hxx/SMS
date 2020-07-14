package com.briup.service;
/** 
* @author 作者 hxx: 
* @version 创建时间：2020年3月27日 下午2:34:01 
* 类说明 
*/

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.briup.bean.Role;

public interface IRoleService {
	Page<Role> findAllRoles();
	Page<Role> findAllRoles(Integer pageIndex);
	Role findRoleById(Integer id);
	void savaOrUpdateRole(Role role);
	//void updateRole(Role role);
	void deleteRole(Integer id);
	List<Integer> findPage(Integer num);
	List<Role> findAllRoleName();
}
