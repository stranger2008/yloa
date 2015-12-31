/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: OrganizeAction.java 
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
import com.lll.common.util.RandomStrUtil;
import com.lll.model.Organize;
import com.lll.service.IOrganizeService;

/**
 * @function 功能 部门action类
 * @author 创建人 李良林
 * @date 创建日期 Sun Jun 09 12:09:55 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/organize/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/organize/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/organize/insert.ftl")
})
 
@Controller
public class OrganizeAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 部门业务层接口
	 */
	@Autowired
	private IOrganizeService organizeService;
	
	/*
	 * 部门对象
	 */
	public Organize organize;
	
	public String org_name_search;
	
	public String up_org_id_search;
	
	public String orgSelect;
	
	public String oneLevelOrgId = "1111111111";
	
	public List organizeList;
	
	
	/**
	 * 方法描述：返回新增部门页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/organize/add")
	public String add() throws Exception {
		orgSelect = getDownSelect(oneLevelOrgId,"");
		return "add";
	}
	
	/**
	 * 方法描述：新增部门
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/organize/insert")
	public String insert() throws Exception {
		organize.setOrg_id(RandomStrUtil.getNumberRand());
		organize.setOrg_level(getOrglevel(organize.getUp_org_id()));
		if(checkFormField(organize,"save")){
			return Constants.ACTIONERROR;
		}
		organizeService.insert(organize);
		this.addActionMessage("部门新增成功");
		return list();
	}
	
	public String getOrglevel(String org_id){
		String org_level = "";
		if(org_id.equals(oneLevelOrgId)){
			org_level = "1";
		}else{
			Organize dOrg = organizeService.get(org_id);
			org_level = String.valueOf(Integer.parseInt(dOrg.getOrg_level())+1);
		}
		return org_level;
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/organize/index")
	public String list() throws Exception {
		orgSelect = getDownSelect(oneLevelOrgId,up_org_id_search);
		Map map = new HashMap();
		if(!StringUtils.isBlank(org_name_search)){
			map.put("org_name", org_name_search);
		}
		if(!StringUtils.isBlank(up_org_id_search)){
			map.put("up_org_id", up_org_id_search);
			
		}
		this.organizeList = organizeService.getList(map);
		return "index";
	}
	
	StringBuffer downSel = new StringBuffer();
	
	public String getDownSelect(String up_org_id,String this_org_id){
		Map oMap = new HashMap();
		oMap.put("up_org_id", up_org_id);
		List list = organizeService.getList(oMap);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				HashMap mMap = (HashMap)list.get(i);
				String org_id = "",org_name = "",org_level = "";
				if(mMap.get("org_id") != null){
					org_id = mMap.get("org_id").toString();
				}
				if(mMap.get("org_name") != null){
					org_name = mMap.get("org_name").toString();
				}
				if(mMap.get("org_level") != null){
					org_level = mMap.get("org_level").toString();
				}
				String m_sel = "";
				if(this_org_id != null && this_org_id.equals(org_id)){
					m_sel = " selected=\"true\" ";
				}
				downSel.append("<option value='"+org_id+"' "+m_sel+">"+getLineStr(org_level)+org_name+"</option>\n");
				getDownSelect(org_id,this_org_id);
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
	 * 方法描述：根据主键找出部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/organize/view")
	public String view() throws Exception {
		
		if(checkFormField(organize,"view")){
			return Constants.ACTIONERROR;
		}
		String id = organize.getOrg_id();
		organize = organizeService.get(id);
		if(organize != null){
			orgSelect = getDownSelect(oneLevelOrgId,organize.getUp_org_id());
		}
		return "view";
	}
	
	/**
	 * 方法描述：修改部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/organize/update")
	public String update() throws Exception {
		organize.setOrg_level(getOrglevel(organize.getUp_org_id()));
		if(checkFormField(organize,"save")){
			return Constants.ACTIONERROR;
		}
		organizeService.update(organize);
		this.addActionMessage("部门修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除部门信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/organize/delete")
	public String delete() throws Exception {
		if(checkFormField(organize,"view")){
			return Constants.ACTIONERROR;
		}
		String id = organize.getOrg_id();
		organizeService.delete(id);
		this.addActionMessage("部门删除成功");
		return list();
	}
	
	@Action("/admin/organize/sort")
	public String updateSort() throws Exception {
		String org_id = organize.getOrg_id();
		String sort_no = organize.getSort_no();
		String org_idStr[]=org_id.split(",");
		String sort_noStr[]=sort_no.split(",");
		List ruleList=new ArrayList();
		if(org_idStr.length>0){
			for(int i=0;i<org_idStr.length;i++){
				Map ruleMap = new HashMap();
				String m_temp = org_idStr[i].trim();
				String s_temp = sort_noStr[i].trim();
				if(!"".equals(m_temp) && !"".equals(s_temp)){
					ruleMap.put("org_id", m_temp);
					ruleMap.put("sort_no", s_temp);
				}
				ruleList.add(ruleMap);
			}
		}
		this.organizeService.updateSort(ruleList);
		this.addActionMessage("部门排序成功");
		return list();
	}
	
	/**
	 * @return the organize
	 */
	public Organize getOrganize() {
		return organize;
	}
	/**
	 * @param Organize
	 *            the organize to set
	 */
	public void setOrganize(Organize organize) {
		this.organize = organize;
	}
	
	
	public String getOrg_name_search() {
		return org_name_search;
	}
	
	public void setOrg_name_search(String org_name_search) {
		this.org_name_search = org_name_search;
	}
	
	public String getUp_org_id_search() {
		return up_org_id_search;
	}
	
	public void setUp_org_id_search(String up_org_id_search) {
		this.up_org_id_search = up_org_id_search;
	}
	

}

