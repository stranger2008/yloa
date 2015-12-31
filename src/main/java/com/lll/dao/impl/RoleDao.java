/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: RoleDao.java 
 */
package com.lll.dao.impl;

import org.springframework.stereotype.Repository;

import com.lll.dao.IRoleDao;
import com.lll.model.Role;

/**
 * @function 功能  角色dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 20:39:55 CST 2013
 */
@Repository
public class RoleDao extends GenericDao<Role,String> implements IRoleDao {
	
	public RoleDao() {
		super(Role.class);
	}
	
}

