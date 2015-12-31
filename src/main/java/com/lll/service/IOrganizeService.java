/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IOrganizeService.java 
 */
package com.lll.service;

import java.util.List;

import com.lll.model.Organize;

/**
 * @function 功能 部门Service层业务接口实现类
 * @author  创建人 李良林
 * @date  创建日期 Sun Jun 09 12:09:55 CST 2013
 */

public interface IOrganizeService extends IGenericService<Organize,String>{
	public void updateSort(List list);
}

