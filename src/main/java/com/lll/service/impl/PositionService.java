/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: PositionService.java 
 */
package com.lll.service.impl;

import java.util.List;

import com.lll.dao.IPositionDao;
import com.lll.model.Position;
import com.lll.service.IPositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 职位Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Sat Jun 08 22:39:26 CST 2013
 */
@Service
public class PositionService extends GenericService<Position,String> implements IPositionService {
	
	IPositionDao positionDao;

	@Autowired
	public PositionService(IPositionDao positionDao) {
		super(positionDao);
		this.positionDao = positionDao;
	}
	
	public void updateSort(List list) {
		positionDao.updateSort(list);
	}
	
}

