/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: OrganizeService.java 
 */
package com.lll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lll.dao.IOrganizeDao;
import com.lll.model.Organize;
import com.lll.service.IOrganizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 部门Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Sun Jun 09 12:09:55 CST 2013
 */
@Service
public class OrganizeService extends GenericService<Organize,String> implements IOrganizeService {
	
	IOrganizeDao organizeDao;

	@Autowired
	public OrganizeService(IOrganizeDao organizeDao) {
		super(organizeDao);
		this.organizeDao = organizeDao;
	}
	public void updateSort(List list) {
		organizeDao.updateSort(list);
	}
}

