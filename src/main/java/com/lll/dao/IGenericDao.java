package com.lll.dao;

import java.util.List;
import java.util.Map;

import com.lll.common.page.PageVo;


public interface IGenericDao<T,PK> {
	
	public String insert(T t);
	
	public void update(T t);
	
	public void delete(PK id);
	
	public List<Map<String,String>> getList(Map<String, String> map);
	
	public int getCount(Map<String, String> map);
	
	public T get(PK id);
	
	public PageVo getPageList(Map map);
}
