/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ParavalueAction.java 
 */
package com.lll.webapp.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lll.common.Constants;
import com.lll.model.Parach;
import com.lll.model.Paravalue;
import com.lll.service.IParachService;
import com.lll.service.IParavalueService;

/**
 * @function 功能 系统参数值action类
 * @author 创建人 李良林
 * @date 创建日期 Mon Aug 05 22:08:27 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/paravalue/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/paravalue/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/paravalue/insert.ftl")
})
 
@Controller
public class ParavalueAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 系统参数值业务层接口
	 */
	@Autowired
	private IParavalueService paravalueService;
	@Autowired
	private IParachService parachService;
	
	/*
	 * 系统参数值对象
	 */
	public Paravalue paravalue;
	
	public Parach parach;
	
	public String val_key_search;
	
	public String ch_code_search;
	
	public String val_value_search;
	
	
	/**
	 * 方法描述：返回新增系统参数值页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/paravalue/add")
	public String add() throws Exception {
		if(!StringUtils.isBlank(ch_code_search)){
			parach = parachService.get(ch_code_search);
		}
		return "add";
	}
	
	/**
	 * 方法描述：新增系统参数值
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/paravalue/insert")
	public String insert() throws Exception {
		if(checkFormField(paravalue,"save")){
			return Constants.ACTIONERROR;
		}
		paravalueService.insert(paravalue);
		this.addActionMessage("系统参数值新增成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/paravalue/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(ch_code_search)){
			map.put("ch_code", ch_code_search);
			parach = parachService.get(ch_code_search);
		}
		
		if(!StringUtils.isBlank(val_key_search)){
			map.put("val_key", val_key_search);
		}
		
		if(!StringUtils.isBlank(val_value_search)){
			map.put("val_value", val_value_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = paravalueService.getPageList(map);
		return "index";
	}
	
	/**
	 * 方法描述：根据主键找出系统参数值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/paravalue/view")
	public String view() throws Exception {
		if(checkFormField(paravalue,"view")){
			return Constants.ACTIONERROR;
		}
		if(!StringUtils.isBlank(ch_code_search)){
			parach = parachService.get(ch_code_search);
		}
		String id = paravalue.getVal_id();
		paravalue = paravalueService.get(id);
		return "view";
	}
	
	/**
	 * 方法描述：修改系统参数值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/paravalue/update")
	public String update() throws Exception {
		if(checkFormField(paravalue,"save")){
			return Constants.ACTIONERROR;
		}
		paravalueService.update(paravalue);
		this.addActionMessage("系统参数值修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除系统参数值信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/paravalue/delete")
	public String delete() throws Exception {
		if(checkFormField(paravalue,"view")){
			return Constants.ACTIONERROR;
		}
		String id = paravalue.getVal_id();
		paravalueService.delete(id);
		this.addActionMessage("系统参数值删除成功");
		return list();
	}
	
	/**
	 * @return the paravalue
	 */
	public Paravalue getParavalue() {
		return paravalue;
	}
	/**
	 * @param Paravalue
	 *            the paravalue to set
	 */
	public void setParavalue(Paravalue paravalue) {
		this.paravalue = paravalue;
	}
	
	
	public String getVal_key_search() {
		return val_key_search;
	}
	
	public void setVal_key_search(String val_key_search) {
		this.val_key_search = val_key_search;
	}
	
	public String getVal_value_search() {
		return val_value_search;
	}
	
	public void setVal_value_search(String val_value_search) {
		this.val_value_search = val_value_search;
	}

	public String getCh_code_search() {
		return ch_code_search;
	}

	public void setCh_code_search(String ch_code_search) {
		this.ch_code_search = ch_code_search;
	}

	public Parach getParach() {
		return parach;
	}

	public void setParach(Parach parach) {
		this.parach = parach;
	}

}

