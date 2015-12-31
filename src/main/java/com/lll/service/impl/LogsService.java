/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: LogsService.java 
 */
package com.lll.service.impl;

import com.lll.dao.ILogsDao;
import com.lll.model.Logs;
import com.lll.service.ILogsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 日志Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 07:17:12 CST 2013
 */
@Service
public class LogsService extends GenericService<Logs,String> implements ILogsService {
	
	ILogsDao logsDao;

	@Autowired
	public LogsService(ILogsDao logsDao) {
		super(logsDao);
		this.logsDao = logsDao;
	}
	
	public void deleteall(){
		logsDao.deleteall();
	}
	
}

