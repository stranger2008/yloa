/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: PositionAction.java 
 */
package com.lll.webapp.action;

import java.util.*;

import com.lll.webapp.action.BaseAction;
import com.lll.model.Position;
import com.lll.service.IPositionService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.lll.common.Constants;

/**
 * @function 功能 职位action类
 * @author 创建人 李良林
 * @date 创建日期 Sat Jun 08 22:39:26 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/position/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/position/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/position/insert.ftl")
})
 
@Controller
public class PositionAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 职位业务层接口
	 */
	@Autowired
	private IPositionService positionService;
	
	/*
	 * 职位对象
	 */
	public Position position;
	
	public String pos_name_search;
	
	
	/**
	 * 方法描述：返回新增职位页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/position/add")
	public String add() throws Exception {
		return "add";
	}
	
	/**
	 * 方法描述：新增职位
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/position/insert")
	public String insert() throws Exception {
		if(checkFormField(position,"save")){
			return Constants.ACTIONERROR;
		}
		positionService.insert(position);
		this.addActionMessage("职位新增成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/position/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(pos_name_search)){
			map.put("pos_name", pos_name_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = positionService.getPageList(map);
		return "index";
	}
	
	/**
	 * 方法描述：根据主键找出职位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/position/view")
	public String view() throws Exception {
		if(checkFormField(position,"view")){
			return Constants.ACTIONERROR;
		}
		String id = position.getPos_id();
		position = positionService.get(id);
		return "view";
	}
	
	/**
	 * 方法描述：修改职位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/position/update")
	public String update() throws Exception {
		if(checkFormField(position,"save")){
			return Constants.ACTIONERROR;
		}
		positionService.update(position);
		this.addActionMessage("职位修改成功");
		return list();
	}
	
	/**
	 * 方法描述：删除职位信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/position/delete")
	public String delete() throws Exception {
		if(checkFormField(position,"view")){
			return Constants.ACTIONERROR;
		}
		String id = position.getPos_id();
		positionService.delete(id);
		this.addActionMessage("职位删除成功");
		return list();
	}
	
	@Action("/admin/position/sort")
	public String updateSort() throws Exception {
		String pos_id = position.getPos_id();
		String sort_no = position.getSort_no();
		String pos_idStr[]=pos_id.split(",");
		String sort_noStr[]=sort_no.split(",");
		List ruleList=new ArrayList();
		if(pos_idStr.length>0){
			for(int i=0;i<pos_idStr.length;i++){
				Map ruleMap = new HashMap();
				String m_temp = pos_idStr[i].trim();
				String s_temp = sort_noStr[i].trim();
				if(!"".equals(m_temp) && !"".equals(s_temp)){
					ruleMap.put("pos_id", m_temp);
					ruleMap.put("sort_no", s_temp);
				}
				ruleList.add(ruleMap);
			}
		}
		this.positionService.updateSort(ruleList);
		this.addActionMessage("职位排序成功");
		return list();
	}
	
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param Position
	 *            the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	public String getPos_name_search() {
		return pos_name_search;
	}
	
	public void setPos_name_search(String pos_name_search) {
		this.pos_name_search = pos_name_search;
	}
	

}

