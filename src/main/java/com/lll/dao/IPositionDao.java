/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao
 * FileName: IPositionDao.java 
 */
package com.lll.dao;

import java.util.List;

import com.lll.model.Position;

/**
 * @function 功能 职位dao层业务接口
 * @author  创建人李良林
 * @date  创建日期 Sat Jun 08 22:39:26 CST 2013
 */

public interface IPositionDao extends IGenericDao<Position,String>{
	public void updateSort(final List list);
}

