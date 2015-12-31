package com.lll.service;

import java.util.List;

import com.lll.model.Menu;

public interface IMenuService extends IGenericService<Menu,String>{
	
	public List getOneMenuList(String menu_right, String user_type);
	
	public String getFirstMenuByList(List list);
	
	public List getMenuListByUpmenuid(String up_menu_id);
	
	public List getTwomenuByRight(String up_menu_id, String menu_right, String user_type);
	
	//通过一级菜单得到所有的三级菜单
	public List getThreeMenuListByOneMenu(String menu_id, String menu_right, String user_type);
	
	public void updateSort(List list);
	
}
