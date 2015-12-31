/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IRolerightService.java 
 */
package com.lll.service;

import java.util.Map;

import com.lll.model.Roleright;

/**
 * @function 功能 操作权限Service层业务接口实现类
 * @author  创建人 李良林
 * @date  创建日期 Thu Jun 13 13:18:48 CST 2013
 */

public interface IRolerightService extends IGenericService<Roleright,String>{
	
	//通过url地址得到权限对应的ID
	public Map getRightidByUrl(String url);
	
	public void deleteByMenuId(String menu_id);
}

