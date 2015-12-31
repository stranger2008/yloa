/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Roleright.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 操作权限实体
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 13:18:48 CST 2013
 */
public class Roleright implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String right_id;
	public String getRight_id() {
		return right_id;
	}
	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}
	
	private String right_name;
	public String getRight_name() {
		return right_name;
	}
	public void setRight_name(String right_name) {
		this.right_name = right_name;
	}
	
	private String menu_id;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Roleright[");
		builder.append(", right_name=");
		builder.append(this.right_name);
		builder.append(", menu_id=");
		builder.append(this.menu_id);
		builder.append(", url=");
		builder.append(this.url);
		builder.append(", type=");
		builder.append(this.type);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}

