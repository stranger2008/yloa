/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: RolerightAction.java 
 */
package com.lll.webapp.action;

import java.util.*;

import com.lll.webapp.action.BaseAction;
import com.lll.model.Roleright;
import com.lll.service.IMenuService;
import com.lll.service.IRolerightService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.lll.common.Constants;
import com.lll.common.util.RandomStrUtil;

/**
 * @function 功能 操作权限action类
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 13:18:48 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/roleright/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/roleright/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/roleright/insert.ftl")
})
 
@Controller
public class RolerightAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 操作权限业务层接口
	 */
	@Autowired
	private IRolerightService rolerightService;
	
	/*
	 * 操作权限对象
	 */
	public Roleright roleright;
	
	public String right_name_search;
	
	public String menu_id_search;
	
	public String url_search;
	
	@Autowired
	public IMenuService menuService;
	
	public String menuSelect;
	public String oneLevelMneuId = "1111111111";
	
	
	/**
	 * 方法描述：返回新增操作权限页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/roleright/add")
	public String add() throws Exception {
		menuSelect = getDownSelect(oneLevelMneuId,"");
		return "add";
	}
	
	StringBuffer downSel = new StringBuffer();
	
	public String getDownSelect(String up_menu_id,String this_menu_id){
		List list = menuService.getMenuListByUpmenuid(up_menu_id);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				HashMap mMap = (HashMap)list.get(i);
				String menu_id = "",menu_name = "",menu_level = "";
				if(mMap.get("menu_id") != null){
					menu_id = mMap.get("menu_id").toString();
				}
				if(mMap.get("menu_name") != null){
					menu_name = mMap.get("menu_name").toString();
				}
				if(mMap.get("menu_level") != null){
					menu_level = mMap.get("menu_level").toString();
				}
				String m_sel = "";
				if(this_menu_id != null && this_menu_id.equals(menu_id)){
					m_sel = " selected=\"true\" ";
				}
				downSel.append("<option value='"+menu_id+"' "+m_sel+">"+getLineStr(menu_level)+menu_name+"</option>\n");
				getDownSelect(menu_id,this_menu_id);
			}
		}
		return downSel.toString();
	}
	
	public String getLineStr(String menu_level){
		int mLevel = Integer.parseInt(menu_level);
		String s = "";
		for(int i=0;i<mLevel;i++){
			s += "-";
		}
		return s;
	}
	
	/**
	 * 方法描述：新增操作权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/roleright/insert")
	public String insert() throws Exception {
		roleright.setRight_id(RandomStrUtil.getNumberRand());
		if(checkFormField(roleright,"save")){
			return Constants.ACTIONERROR;
		}
		rolerightService.insert(roleright);
		this.addActionMessage("操作权限新增成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/roleright/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(right_name_search)){
			map.put("right_name", right_name_search);
		}
		
		if(!StringUtils.isBlank(menu_id_search)){
			map.put("menu_id", menu_id_search);
		}
		
		if(!StringUtils.isBlank(url_search)){
			map.put("url", url_search);
		}
		menuSelect = getDownSelect(oneLevelMneuId,menu_id_search);
		
		map = this.pageTool(map);
		this.pagevo = rolerightService.getPageList(map);
		return "index";
	}
	
	/**
	 * 方法描述：根据主键找出操作权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/roleright/view")
	public String view() throws Exception {
		if(checkFormField(roleright,"view")){
			return Constants.ACTIONERROR;
		}
		String id = roleright.getRight_id();
		roleright = rolerightService.get(id);
		menuSelect = getDownSelect(oneLevelMneuId,roleright.getMenu_id());
		return "view";
	}
	
	/**
	 * 方法描述：修改操作权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/roleright/update")
	public String update() throws Exception {
		if(checkFormField(roleright,"save")){
			return Constants.ACTIONERROR;
		}
		rolerightService.update(roleright);
		this.addActionMessage("操作权限修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除操作权限信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/roleright/delete")
	public String delete() throws Exception {
		if(checkFormField(roleright,"view")){
			return Constants.ACTIONERROR;
		}
		String id = roleright.getRight_id();
		rolerightService.delete(id);
		this.addActionMessage("操作权限删除成功");
		return list();
	}
	
	/**
	 * @return the roleright
	 */
	public Roleright getRoleright() {
		return roleright;
	}
	/**
	 * @param Roleright
	 *            the roleright to set
	 */
	public void setRoleright(Roleright roleright) {
		this.roleright = roleright;
	}
	
	
	public String getRight_name_search() {
		return right_name_search;
	}
	
	public void setRight_name_search(String right_name_search) {
		this.right_name_search = right_name_search;
	}
	
	public String getMenu_id_search() {
		return menu_id_search;
	}
	
	public void setMenu_id_search(String menu_id_search) {
		this.menu_id_search = menu_id_search;
	}
	
	public String getUrl_search() {
		return url_search;
	}
	
	public void setUrl_search(String url_search) {
		this.url_search = url_search;
	}
	

}

