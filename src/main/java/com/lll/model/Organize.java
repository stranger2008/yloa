/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Organize.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 部门实体
 * @author 创建人 李良林
 * @date 创建日期 Sun Jun 09 12:09:55 CST 2013
 */
public class Organize implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String org_level;
	
	private String org_id;
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	private String org_name;
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	private String up_org_id;
	public String getUp_org_id() {
		return up_org_id;
	}
	public void setUp_org_id(String up_org_id) {
		this.up_org_id = up_org_id;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String org_desc;
	public String getOrg_desc() {
		return org_desc;
	}
	public void setOrg_desc(String org_desc) {
		this.org_desc = org_desc;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organize[");
		builder.append(", org_name=");
		builder.append(this.org_name);
		builder.append(", up_org_id=");
		builder.append(this.up_org_id);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", org_desc=");
		builder.append(this.org_desc);
		builder.append("]");
		return builder.toString();
	}
	public String getOrg_level() {
		return org_level;
	}
	public void setOrg_level(String org_level) {
		this.org_level = org_level;
	}

}

