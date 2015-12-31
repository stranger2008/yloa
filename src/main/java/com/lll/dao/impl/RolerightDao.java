/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: RolerightDao.java 
 */
package com.lll.dao.impl;

import org.springframework.stereotype.Repository;

import com.lll.dao.IRolerightDao;
import com.lll.model.Roleright;

/**
 * @function 功能  操作权限dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 13:18:48 CST 2013
 */
@Repository
public class RolerightDao extends GenericDao<Roleright,String> implements IRolerightDao {
	
	public RolerightDao() {
		super(Roleright.class);
	}
	
	public void deleteByMenuId(String menu_id){
		this.getSqlMapClientTemplate().delete("roleright.deleteByMenuId",menu_id);
	}
	
}

