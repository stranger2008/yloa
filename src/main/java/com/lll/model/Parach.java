/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Parach.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 系统参数实体
 * @author 创建人 李良林
 * @date 创建日期 Sun Aug 04 14:20:40 CST 2013
 */
public class Parach implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ch_code;
	public String getCh_code() {
		return ch_code;
	}
	public void setCh_code(String ch_code) {
		this.ch_code = ch_code;
	}
	
	private String ch_name;
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	
	private String is_edit;
	public String getIs_edit() {
		return is_edit;
	}
	public void setIs_edit(String is_edit) {
		this.is_edit = is_edit;
	}
	
	private String value_len;
	public String getValue_len() {
		return value_len;
	}
	public void setValue_len(String value_len) {
		this.value_len = value_len;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parach[");
		builder.append(", ch_name=");
		builder.append(this.ch_name);
		builder.append(", is_edit=");
		builder.append(this.is_edit);
		builder.append(", value_len=");
		builder.append(this.value_len);
		builder.append("]");
		return builder.toString();
	}

}

