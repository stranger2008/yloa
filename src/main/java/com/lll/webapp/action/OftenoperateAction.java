/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: OftenoperateAction.java 
 */
package com.lll.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lll.common.Constants;
import com.lll.model.Oftenoperate;
import com.lll.model.Roleright;
import com.lll.service.IOftenoperateService;
import com.lll.service.IRolerightService;

/**
 * @function 功能 常用操作action类
 * @author 创建人 李良林
 * @date 创建日期 Wed Jun 19 23:09:46 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/oftenoperate/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/oftenoperate/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/oftenoperate/insert.ftl")
})
 
@Controller
public class OftenoperateAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 常用操作业务层接口
	 */
	@Autowired
	private IOftenoperateService oftenoperateService;
	
	/*
	 * 常用操作对象
	 */
	public Oftenoperate oftenoperate;
	
	public String oper_name_search;
	
	public String oper_url_search;
	
	
	/**
	 * 方法描述：返回新增常用操作页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/oftenoperate/add")
	public String add() throws Exception {
		return "add";
	}
	
	/**
	 * 方法描述：新增常用操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/oftenoperate/insert")
	public String insert() throws Exception {
		if(checkFormField(oftenoperate,"save")){
			return Constants.ACTIONERROR;
		}
		oftenoperateService.insert(oftenoperate);
		this.addActionMessage("常用操作新增成功");
		return list();
	}
	
	@Autowired
	private IRolerightService rolerightService;
	
	public String right_id;
	
	@Action("/admin/oftenoperate/addop")
	public String addop() throws Exception {
		String up_menu_id = this.one_menu_id;
		String tipStr = "0";
		if(right_id != null){
			Roleright rr = rolerightService.get(right_id);
			String right_name = rr.getRight_name();
			Map map = new HashMap();
			map.put("oper_name", right_name);
			List rList = oftenoperateService.getList(map);
			if(rList != null && rList.size() > 0){
				//重复添加
				tipStr = "1";
			}else{
				String url = rr.getUrl();
				if(url.indexOf(",") > -1){
					url = url.substring(0, url.indexOf(","));
				}
				if(url.startsWith("/")){
					url = url.substring(1,url.length());
				}
				Oftenoperate oo = new Oftenoperate();
				oo.setOper_name(right_name);
				oo.setOper_url(url+"?one_menu_id="+up_menu_id);
				oo.setSort_no("1");
				this.oftenoperateService.insert(oo);
				//添加成功
				tipStr = "2";
			}
		}
		response.getWriter().print(tipStr);
		return null;
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/oftenoperate/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(oper_name_search)){
			map.put("oper_name", oper_name_search);
		}
		
		if(!StringUtils.isBlank(oper_url_search)){
			map.put("oper_url", oper_url_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = oftenoperateService.getPageList(map);
		return "index";
	}
	
	/**
	 * 方法描述：根据主键找出常用操作信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/oftenoperate/view")
	public String view() throws Exception {
		if(checkFormField(oftenoperate,"view")){
			return Constants.ACTIONERROR;
		}
		String id = oftenoperate.getTrade_id();
		oftenoperate = oftenoperateService.get(id);
		return "view";
	}
	
	/**
	 * 方法描述：修改常用操作信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/oftenoperate/update")
	public String update() throws Exception {
		if(checkFormField(oftenoperate,"save")){
			return Constants.ACTIONERROR;
		}
		oftenoperateService.update(oftenoperate);
		this.addActionMessage("常用操作修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除常用操作信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/oftenoperate/delete")
	public String delete() throws Exception {
		if(checkFormField(oftenoperate,"view")){
			return Constants.ACTIONERROR;
		}
		String id = oftenoperate.getTrade_id();
		oftenoperateService.delete(id);
		this.addActionMessage("常用操作删除成功");
		return list();
	}
	
	@Action("/admin/oftenoperate/sort")
	public String updateSort() throws Exception {
		String trade_id = oftenoperate.getTrade_id();
		String sort_no = oftenoperate.getSort_no();
		String trade_idStr[]=trade_id.split(",");
		String sort_noStr[]=sort_no.split(",");
		List ruleList=new ArrayList();
		if(trade_idStr.length>0){
			for(int i=0;i<trade_idStr.length;i++){
				Map ruleMap = new HashMap();
				String m_temp = trade_idStr[i].trim();
				String s_temp = sort_noStr[i].trim();
				if(!"".equals(m_temp) && !"".equals(s_temp)){
					ruleMap.put("trade_id", m_temp);
					ruleMap.put("sort_no", s_temp);
				}
				ruleList.add(ruleMap);
			}
		}
		this.oftenoperateService.updateSort(ruleList);
		this.addActionMessage("常用操作排序成功");
		return list();
	}
	
	/**
	 * @return the oftenoperate
	 */
	public Oftenoperate getOftenoperate() {
		return oftenoperate;
	}
	/**
	 * @param Oftenoperate
	 *            the oftenoperate to set
	 */
	public void setOftenoperate(Oftenoperate oftenoperate) {
		this.oftenoperate = oftenoperate;
	}
	
	
	public String getOper_name_search() {
		return oper_name_search;
	}
	
	public void setOper_name_search(String oper_name_search) {
		this.oper_name_search = oper_name_search;
	}
	
	public String getOper_url_search() {
		return oper_url_search;
	}
	
	public void setOper_url_search(String oper_url_search) {
		this.oper_url_search = oper_url_search;
	}

	public String getRight_id() {
		return right_id;
	}

	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}

}

