/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IOftenoperateService.java 
 */
package com.lll.service;

import java.util.List;

import com.lll.model.Oftenoperate;

/**
 * @function 功能 常用操作Service层业务接口实现类
 * @author  创建人 李良林
 * @date  创建日期 Wed Jun 19 23:09:46 CST 2013
 */

public interface IOftenoperateService extends IGenericService<Oftenoperate,String>{
	public void updateSort(List list);
}

