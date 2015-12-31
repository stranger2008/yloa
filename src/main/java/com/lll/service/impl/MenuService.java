package com.lll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lll.dao.IMenuDao;
import com.lll.model.Menu;
import com.lll.service.IMenuService;

@Service
public class MenuService extends GenericService<Menu,String> implements IMenuService {
	
	IMenuDao menuDao;

	@Autowired
	public MenuService(IMenuDao menuDao) {
		super(menuDao);
		this.menuDao = menuDao;
	}
	
	//获取一级菜单
	public List getOneMenuList(String menu_right,String user_type){
		Map map = new HashMap();
		map.put("syscode", "sys");
		map.put("menu_level", "1");
		//user_type 0:普通管理员 1：超级管理员 
		//超级管理员不权限控制，拥有所有菜单和操作权限
		if(user_type.equals("0") || user_type.equals("")){
			//如果没有菜单权限，程序自动给出10个0，为了让程序不报错
			//因为菜单ID是不会出现0数字，这样肯定是找不到数据的
			if(menu_right.trim().equals("")){
				menu_right = "0000000000";
			}
			map.put("menu_right", menu_right);
		}
		return getList(map);
	}

	
	public String getFirstMenuByList(List list) {
		String menu_id = "";
		if(list != null && list.size()>0){
			Map map = (HashMap)list.get(0);
			if(map.get("menu_id") != null){
				menu_id = map.get("menu_id").toString();
			}
		}
		return menu_id;
	}
	
	public List getMenuListByUpmenuid(String up_menu_id){
		Map map = new HashMap();
		map.put("up_menu_id", up_menu_id);
		return getList(map);
	}
	
	public List getTwomenuByRight(String up_menu_id,String menu_right,String user_type){
		Map map = new HashMap();
		map.put("up_menu_id", up_menu_id);
		//user_type 0:普通管理员 1：超级管理员 
		//超级管理员不权限控制，拥有所有菜单和操作权限
		if(user_type.equals("0") || user_type.equals("")){
			//如果没有菜单权限，程序自动给出10个0，为了让程序不报错
			//因为菜单ID是不会出现0数字，这样肯定是找不到数据的
			if(menu_right.trim().equals("")){
				menu_right = "0000000000";
			}
			map.put("menu_right", menu_right);
		}
		return getList(map);
	}
	
	public List getThreeMenuListByOneMenu(String menu_id,String menu_right,String user_type){
		Map map = new HashMap();
		map.put("menu_id", menu_id);
		//user_type 0:普通管理员 1：超级管理员 
		//超级管理员不权限控制，拥有所有菜单和操作权限
		if(user_type.equals("0") || user_type.equals("")){
			//如果没有菜单权限，程序自动给出10个0，为了让程序不报错
			//因为菜单ID是不会出现0数字，这样肯定是找不到数据的
			if(menu_right.trim().equals("")){
				menu_right = "0000000000";
			}
			map.put("menu_right", menu_right);
		}
		return menuDao.getThreeMenuListByOneMenu(map);
	}

	public void updateSort(List list) {
		menuDao.updateSort(list);
	}
	
}
