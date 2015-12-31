package com.lll.dao;

import java.util.List;
import java.util.Map;

import com.lll.model.Menu;

public interface IMenuDao extends IGenericDao<Menu,String>{

	public List getThreeMenuListByOneMenu(Map map);
	
	public void updateSort(final List list);
	
}
