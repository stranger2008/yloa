/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao
 * FileName: IOftenoperateDao.java 
 */
package com.lll.dao;

import java.util.List;

import com.lll.model.Oftenoperate;

/**
 * @function 功能 常用操作dao层业务接口
 * @author  创建人李良林
 * @date  创建日期 Wed Jun 19 23:09:46 CST 2013
 */

public interface IOftenoperateDao extends IGenericDao<Oftenoperate,String>{
	public void updateSort(final List list);
}

