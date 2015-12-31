/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Role.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 角色实体
 * @author 创建人 李良林
 * @date 创建日期 Thu Jun 13 20:39:55 CST 2013
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String role_id;
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	private String role_name;
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	private String menu_right;
	public String getMenu_right() {
		return menu_right;
	}
	public void setMenu_right(String menu_right) {
		this.menu_right = menu_right;
	}
	
	private String oper_right;
	public String getOper_right() {
		return oper_right;
	}
	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
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
		builder.append("Role[");
		builder.append(", role_name=");
		builder.append(this.role_name);
		builder.append(", menu_right=");
		builder.append(this.menu_right);
		builder.append(", oper_right=");
		builder.append(this.oper_right);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}

