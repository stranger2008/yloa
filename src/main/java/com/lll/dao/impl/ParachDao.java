/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: ParachDao.java 
 */
package com.lll.dao.impl;

import org.springframework.stereotype.Repository;

import com.lll.dao.IParachDao;
import com.lll.model.Parach;

/**
 * @function 功能  系统参数dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Sun Aug 04 14:20:40 CST 2013
 */
@Repository
public class ParachDao extends GenericDao<Parach,String> implements IParachDao {
	
	public ParachDao() {
		super(Parach.class);
	}
	
}

