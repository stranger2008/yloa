/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao
 * FileName: ILogsDao.java 
 */
package com.lll.dao;

import com.lll.model.Logs;

/**
 * @function 功能 日志dao层业务接口
 * @author  创建人李良林
 * @date  创建日期 Thu Jun 13 07:17:12 CST 2013
 */

public interface ILogsDao extends IGenericDao<Logs,String>{
	public void deleteall();
}

