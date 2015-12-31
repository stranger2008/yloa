package com.lll.service;

import java.util.List;
import java.util.Map;

import com.lll.common.page.PageVo;


public interface IGenericService<T,PK> {
	
	/**
	 * 新增记录，可获得返回的主键
	 */
	public String insert(T t);
	
	/**
	 * 修改记录
	 */
	public void update(T t);
	
	/**
	 * 按主键删除
	 */
	public void delete(PK id);
	
	/**
	 * 查询记录
	 */
	public List<Map<String,String>> getList(Map<String, String> map);
	
	/**
	 * 查询记录数量
	 */
	public int getCount(Map<String, String> map);
	
	/**
     *按主键查询,取一条记录
     */
	public T get(PK id);
	
	public PageVo getPageList(Map map);

}
