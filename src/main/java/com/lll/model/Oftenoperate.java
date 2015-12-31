/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Oftenoperate.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 常用操作实体
 * @author 创建人 李良林
 * @date 创建日期 Wed Jun 19 23:09:46 CST 2013
 */
public class Oftenoperate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String oper_name;
	public String getOper_name() {
		return oper_name;
	}
	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}
	
	private String oper_url;
	public String getOper_url() {
		return oper_url;
	}
	public void setOper_url(String oper_url) {
		this.oper_url = oper_url;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Oftenoperate[");
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", oper_name=");
		builder.append(this.oper_name);
		builder.append(", oper_url=");
		builder.append(this.oper_url);
		builder.append("]");
		return builder.toString();
	}

}

