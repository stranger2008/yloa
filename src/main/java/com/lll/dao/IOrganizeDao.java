/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao
 * FileName: IOrganizeDao.java 
 */
package com.lll.dao;

import java.util.List;

import com.lll.model.Organize;

/**
 * @function 功能 部门dao层业务接口
 * @author  创建人李良林
 * @date  创建日期 Sun Jun 09 12:09:55 CST 2013
 */

public interface IOrganizeDao extends IGenericDao<Organize,String>{
	public void updateSort(final List list);
}

