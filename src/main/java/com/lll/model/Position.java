/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Position.java 
 */
package com.lll.model;

import java.io.Serializable;
/**
 * @function 功能 职位实体
 * @author 创建人 李良林
 * @date 创建日期 Sat Jun 08 22:39:26 CST 2013
 */
public class Position implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String pos_id;
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	
	private String pos_name;
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String pos_desc;
	public String getPos_desc() {
		return pos_desc;
	}
	public void setPos_desc(String pos_desc) {
		this.pos_desc = pos_desc;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position[");
		builder.append(", pos_name=");
		builder.append(this.pos_name);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", pos_desc=");
		builder.append(this.pos_desc);
		builder.append("]");
		return builder.toString();
	}

}

