/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: ParachService.java 
 */
package com.lll.service.impl;

import com.lll.dao.IParachDao;
import com.lll.model.Parach;
import com.lll.service.IParachService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 系统参数Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Sun Aug 04 14:20:40 CST 2013
 */
@Service
public class ParachService extends GenericService<Parach,String> implements IParachService {
	
	IParachDao parachDao;

	@Autowired
	public ParachService(IParachDao parachDao) {
		super(parachDao);
		this.parachDao = parachDao;
	}
	
}

