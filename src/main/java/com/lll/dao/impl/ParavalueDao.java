/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: ParavalueDao.java 
 */
package com.lll.dao.impl;

import org.springframework.stereotype.Repository;

import com.lll.dao.IParavalueDao;
import com.lll.model.Paravalue;

/**
 * @function 功能  系统参数值dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Mon Aug 05 22:08:27 CST 2013
 */
@Repository
public class ParavalueDao extends GenericDao<Paravalue,String> implements IParavalueDao {
	
	public ParavalueDao() {
		super(Paravalue.class);
	}
	
}

