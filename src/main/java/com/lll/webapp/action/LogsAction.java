/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: LogsAction.java 
 */
package com.lll.webapp.action;

import java.util.*;
import com.lll.webapp.action.BaseAction;
import com.lll.model.Logs;
import com.lll.service.ILogsService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.lll.common.Constants;

/**
 * @function 功能 日志action类
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 07:17:12 CST 2013
 */
 
@Results( { 
	@Result(name = "index", location = "/WEB-INF/pages/admin/logs/index.ftl")
})
 
@Controller
public class LogsAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 日志业务层接口
	 */
	@Autowired
	private ILogsService logsService;
	
	/*
	 * 日志对象
	 */
	public Logs logs;
	
	public String content_search;
	
	public String ipaddr_search;
	
	public String in_date_start_search,in_date_end_search;
	
	public String user_name_search;
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/logs/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(content_search)){
			map.put("content", content_search);
		}
		
		if(!StringUtils.isBlank(ipaddr_search)){
			map.put("ipaddr", ipaddr_search);
		}
		
		if(!StringUtils.isBlank(in_date_start_search)){
			map.put("start_date", in_date_start_search);
		}
		
		if(!StringUtils.isBlank(in_date_end_search)){
			map.put("end_date", in_date_end_search);
		}
		
		if(!StringUtils.isBlank(user_name_search)){
			map.put("user_name", user_name_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = logsService.getPageList(map);
		return "index";
	}
	
	
	
	/**
	 * 方法描述：删除日志信息
	 * @return
	 * @throws Exception
	 */
	@Action("/admin/logs/delete")
	public String delete() throws Exception {
		if(checkFormField(logs,"view")){
			return Constants.ACTIONERROR;
		}
		String id = logs.getLog_id();
		logsService.delete(id);
		this.addActionMessage("日志删除成功");
		return list();
	}
	
	
	@Action("/admin/logs/deleteall")
	public String deleteall() throws Exception {
		logsService.deleteall();
		this.addActionMessage("清空日志成功");
		return list();
	}
	
	/**
	 * @return the logs
	 */
	public Logs getLogs() {
		return logs;
	}
	/**
	 * @param Logs
	 *            the logs to set
	 */
	public void setLogs(Logs logs) {
		this.logs = logs;
	}
	
	
	public String getContent_search() {
		return content_search;
	}
	
	public void setContent_search(String content_search) {
		this.content_search = content_search;
	}
	
	public String getIpaddr_search() {
		return ipaddr_search;
	}
	
	public void setIpaddr_search(String ipaddr_search) {
		this.ipaddr_search = ipaddr_search;
	}
	
	public String getIn_date_start_search() {
		return in_date_start_search;
	}

	public void setIn_date_start_search(String in_date_start_search) {
		this.in_date_start_search = in_date_start_search;
	}

	public String getIn_date_end_search() {
		return in_date_end_search;
	}

	public void setIn_date_end_search(String in_date_end_search) {
		this.in_date_end_search = in_date_end_search;
	}

	public String getUser_name_search() {
		return user_name_search;
	}
	
	public void setUser_name_search(String user_name_search) {
		this.user_name_search = user_name_search;
	}
	

}

