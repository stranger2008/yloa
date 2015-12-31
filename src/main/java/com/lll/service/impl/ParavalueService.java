/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: ParavalueService.java 
 */
package com.lll.service.impl;

import com.lll.dao.IParavalueDao;
import com.lll.model.Paravalue;
import com.lll.service.IParavalueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 系统参数值Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Mon Aug 05 22:08:27 CST 2013
 */
@Service
public class ParavalueService extends GenericService<Paravalue,String> implements IParavalueService {
	
	IParavalueDao paravalueDao;

	@Autowired
	public ParavalueService(IParavalueDao paravalueDao) {
		super(paravalueDao);
		this.paravalueDao = paravalueDao;
	}
	
}

