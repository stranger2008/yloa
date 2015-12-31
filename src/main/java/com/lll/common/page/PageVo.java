package com.lll.common.page;

import java.util.List;

public class PageVo {
	
	public List list;
	
	public int count;
	
	public String pageString;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	
}
