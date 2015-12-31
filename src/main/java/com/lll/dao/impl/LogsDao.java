/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: LogsDao.java 
 */
package com.lll.dao.impl;

import org.springframework.stereotype.Repository;

import com.lll.dao.ILogsDao;
import com.lll.model.Logs;

/**
 * @function 功能  日志dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 07:17:12 CST 2013
 */
@Repository
public class LogsDao extends GenericDao<Logs,String> implements ILogsDao {
	
	public LogsDao() {
		super(Logs.class);
	}
	
	public void deleteall(){
		this.getSqlMapClientTemplate().delete("logs.deleteall");
	}
}

