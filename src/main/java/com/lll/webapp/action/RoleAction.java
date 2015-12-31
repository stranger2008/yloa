/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: RoleAction.java 
 */
package com.lll.webapp.action;

import java.util.*;

import com.lll.webapp.action.BaseAction;
import com.lll.model.Role;
import com.lll.service.IRoleService;
import com.lll.service.IRolerightService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.lll.common.Constants;

/**
 * @function 功能 角色action类
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 20:39:55 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/role/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/role/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/role/insert.ftl")
})
 
@Controller
public class RoleAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 角色业务层接口
	 */
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRolerightService rolerightService;
	
	/*
	 * 角色对象
	 */
	public Role role;
	
	public String role_name_search;
	
	public List menuList;
	public List rolerightList;
	
	
	/**
	 * 方法描述：返回新增角色页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/role/add")
	public String add() throws Exception {
		Map map = new HashMap();
		this.menuList = menuService.getList(map);
		this.rolerightList = rolerightService.getList(map);
		return "add";
	}
	
	/**
	 * 方法描述：新增角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/role/insert")
	public String insert() throws Exception {
		if(checkFormField(role,"save")){
			return Constants.ACTIONERROR;
		}
		if(role.getMenu_right()!=null){
			role.setMenu_right(role.getMenu_right().replace(" ", ""));
		}
		if(role.getOper_right()!=null){
			role.setOper_right(role.getOper_right().replace(" ", ""));
		}
		roleService.insert(role);
		this.addActionMessage("角色新增成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/role/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(role_name_search)){
			map.put("role_name", role_name_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = roleService.getPageList(map);
		return "index";
	}
	
	/**
	 * 方法描述：根据主键找出角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/role/view")
	public String view() throws Exception {
		if(checkFormField(role,"view")){
			return Constants.ACTIONERROR;
		}
		Map map = new HashMap();
		this.menuList = menuService.getList(map);
		this.rolerightList = rolerightService.getList(map);
		String id = role.getRole_id();
		role = roleService.get(id);
		return "view";
	}
	
	/**
	 * 方法描述：修改角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/role/update")
	public String update() throws Exception {
		if(checkFormField(role,"save")){
			return Constants.ACTIONERROR;
		}
		if(role.getMenu_right()!=null){
			role.setMenu_right(role.getMenu_right().replace(" ", ""));
		}
		if(role.getOper_right()!=null){
			role.setOper_right(role.getOper_right().replace(" ", ""));
		}
		roleService.update(role);
		this.addActionMessage("角色修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除角色信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/role/delete")
	public String delete() throws Exception {
		if(checkFormField(role,"view")){
			return Constants.ACTIONERROR;
		}
		String id = role.getRole_id();
		roleService.delete(id);
		this.addActionMessage("角色删除成功");
		return list();
	}
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param Role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	public String getRole_name_search() {
		return role_name_search;
	}
	
	public void setRole_name_search(String role_name_search) {
		this.role_name_search = role_name_search;
	}
	

}

