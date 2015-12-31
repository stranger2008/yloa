/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Logs.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 日志实体
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 07:17:12 CST 2013
 */
public class Logs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String log_id;
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String ipaddr;
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Logs[");
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", ipaddr=");
		builder.append(this.ipaddr);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append("]");
		return builder.toString();
	}
	
	public Logs() {
		super();
	}
	public Logs(String content, String ipaddr, String user_name) {
		super();
		this.content = content;
		this.ipaddr = ipaddr;
		this.user_name = user_name;
	}

}

