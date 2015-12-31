/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Paravalue.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 系统参数值实体
 * @author 创建人 李良林
 * @date 创建日期 Mon Aug 05 22:08:27 CST 2013
 */
public class Paravalue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String val_id;
	public String getVal_id() {
		return val_id;
	}
	public void setVal_id(String val_id) {
		this.val_id = val_id;
	}
	
	private String ch_code;
	public String getCh_code() {
		return ch_code;
	}
	public void setCh_code(String ch_code) {
		this.ch_code = ch_code;
	}
	
	private String val_key;
	public String getVal_key() {
		return val_key;
	}
	public void setVal_key(String val_key) {
		this.val_key = val_key;
	}
	
	private String val_value;
	public String getVal_value() {
		return val_value;
	}
	public void setVal_value(String val_value) {
		this.val_value = val_value;
	}
	
	private String rsrv1;
	public String getRsrv1() {
		return rsrv1;
	}
	public void setRsrv1(String rsrv1) {
		this.rsrv1 = rsrv1;
	}
	
	private String rsrv2;
	public String getRsrv2() {
		return rsrv2;
	}
	public void setRsrv2(String rsrv2) {
		this.rsrv2 = rsrv2;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paravalue[");
		builder.append(", ch_code=");
		builder.append(this.ch_code);
		builder.append(", val_key=");
		builder.append(this.val_key);
		builder.append(", val_value=");
		builder.append(this.val_value);
		builder.append(", rsrv1=");
		builder.append(this.rsrv1);
		builder.append(", rsrv2=");
		builder.append(this.rsrv2);
		builder.append("]");
		return builder.toString();
	}

}

