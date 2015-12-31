/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao
 * FileName: IRolerightDao.java 
 */
package com.lll.dao;

import com.lll.model.Roleright;

/**
 * @function 功能 操作权限dao层业务接口
 * @author  创建人李良林
 * @date  创建日期 Thu Jun 13 13:18:48 CST 2013
 */

public interface IRolerightDao extends IGenericDao<Roleright,String>{
	public void deleteByMenuId(String menu_id);
}

