/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: OftenoperateService.java 
 */
package com.lll.service.impl;

import java.util.List;

import com.lll.dao.IOftenoperateDao;
import com.lll.model.Oftenoperate;
import com.lll.service.IOftenoperateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 常用操作Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Wed Jun 19 23:09:46 CST 2013
 */
@Service
public class OftenoperateService extends GenericService<Oftenoperate,String> implements IOftenoperateService {
	
	IOftenoperateDao oftenoperateDao;

	@Autowired
	public OftenoperateService(IOftenoperateDao oftenoperateDao) {
		super(oftenoperateDao);
		this.oftenoperateDao = oftenoperateDao;
	}
	public void updateSort(List list) {
		oftenoperateDao.updateSort(list);
	}
}

