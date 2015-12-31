/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ILogsService.java 
 */
package com.lll.service;

import com.lll.model.Logs;

/**
 * @function 功能 日志Service层业务接口实现类
 * @author  创建人 李良林
 * @date  创建日期 Thu Jun 13 07:17:12 CST 2013
 */

public interface ILogsService extends IGenericService<Logs,String>{
	public void deleteall();
}

