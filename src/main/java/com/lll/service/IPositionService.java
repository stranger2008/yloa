/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IPositionService.java 
 */
package com.lll.service;

import java.util.List;

import com.lll.model.Position;

/**
 * @function 功能 职位Service层业务接口实现类
 * @author  创建人 李良林
 * @date  创建日期 Sat Jun 08 22:39:26 CST 2013
 */

public interface IPositionService extends IGenericService<Position,String>{
	public void updateSort(List list);
}

