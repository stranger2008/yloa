/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ParachAction.java 
 */
package com.lll.webapp.action;

import java.util.*;
import com.lll.webapp.action.BaseAction;
import com.lll.model.Parach;
import com.lll.service.IParachService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.lll.common.Constants;

/**
 * @function 功能 系统参数action类
 * @author 创建人 李良林
 * @date 创建日期 Sun Aug 04 14:20:40 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/parach/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/parach/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/parach/insert.ftl")
})
 
@Controller
public class ParachAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 系统参数业务层接口
	 */
	@Autowired
	private IParachService parachService;
	
	/*
	 * 系统参数对象
	 */
	public Parach parach;
	
	public String ch_name_search;
	
	
	/**
	 * 方法描述：返回新增系统参数页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/parach/add")
	public String add() throws Exception {
		return "add";
	}
	
	/**
	 * 方法描述：新增系统参数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/parach/insert")
	public String insert() throws Exception {
		if(checkFormField(parach,"save")){
			return Constants.ACTIONERROR;
		}
		parachService.insert(parach);
		this.addActionMessage("系统参数新增成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/parach/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(ch_name_search)){
			map.put("ch_name", ch_name_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = parachService.getPageList(map);
		return "index";
	}
	
	/**
	 * 方法描述：根据主键找出系统参数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/parach/view")
	public String view() throws Exception {
		if(checkFormField(parach,"view")){
			return Constants.ACTIONERROR;
		}
		String id = parach.getCh_code();
		parach = parachService.get(id);
		return "view";
	}
	
	/**
	 * 方法描述：修改系统参数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/parach/update")
	public String update() throws Exception {
		if(checkFormField(parach,"save")){
			return Constants.ACTIONERROR;
		}
		parachService.update(parach);
		this.addActionMessage("系统参数修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除系统参数信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/parach/delete")
	public String delete() throws Exception {
		if(checkFormField(parach,"view")){
			return Constants.ACTIONERROR;
		}
		String id = parach.getCh_code();
		parachService.delete(id);
		this.addActionMessage("系统参数删除成功");
		return list();
	}
	
	/**
	 * @return the parach
	 */
	public Parach getParach() {
		return parach;
	}
	/**
	 * @param Parach
	 *            the parach to set
	 */
	public void setParach(Parach parach) {
		this.parach = parach;
	}
	
	
	public String getCh_name_search() {
		return ch_name_search;
	}
	
	public void setCh_name_search(String ch_name_search) {
		this.ch_name_search = ch_name_search;
	}
	

}

