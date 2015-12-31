/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: RoleService.java 
 */
package com.lll.service.impl;

import com.lll.dao.IRoleDao;
import com.lll.model.Role;
import com.lll.service.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 角色Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 20:39:55 CST 2013
 */
@Service
public class RoleService extends GenericService<Role,String> implements IRoleService {
	
	IRoleDao roleDao;

	@Autowired
	public RoleService(IRoleDao roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}
	
}

