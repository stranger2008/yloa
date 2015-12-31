/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.servie.impl
 * FileName: RolerightService.java 
 */
package com.lll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lll.dao.IRolerightDao;
import com.lll.model.Roleright;
import com.lll.service.IRolerightService;


/**
 * @function 功能 操作权限Service层业务接口实现
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 13:18:48 CST 2013
 */
@Service
public class RolerightService extends GenericService<Roleright,String> implements IRolerightService {
	
	IRolerightDao rolerightDao;

	@Autowired
	public RolerightService(IRolerightDao rolerightDao) {
		super(rolerightDao);
		this.rolerightDao = rolerightDao;
	}
	
	public Map getRightidByUrl(String url){
		Map map = new HashMap();
		map.put("url", url);
		List list = rolerightDao.getList(map);
		String right_id = "";
		Map rMap = new HashMap();
		if(list != null && list.size()>0){
			rMap = (Map)list.get(0);
		}
		return rMap;
	}
	
	public void deleteByMenuId(String menu_id){
		this.rolerightDao.deleteByMenuId(menu_id);
	}
}

