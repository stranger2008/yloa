package com.lll.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.lll.common.dialect.LimitSqlExecutor;
import com.lll.common.dialect.ReflectUtil;
import com.lll.common.page.PageUtil;
import com.lll.common.page.PageVo;
import com.lll.dao.IGenericDao;

@Repository
public abstract class GenericDao<T,PK> extends SqlMapClientDaoSupport implements IGenericDao<T,PK> {

	private Class<T> persistentClass;
	@Autowired
	private SqlExecutor sqlExecutor;
	
	@Autowired
	private SqlMapClient sqlMapClient;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}
	public SqlExecutor getSqlExecutor() {
		return sqlExecutor;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}

	public void setEnableLimit(boolean enableLimit) {
		if (sqlExecutor instanceof LimitSqlExecutor)
			((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
	}
	public void initialize() {
		try {
			if (sqlExecutor != null) {
				com.ibatis.sqlmap.client.SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
				if (sqlMapClient instanceof ExtendedSqlMapClient)
					ReflectUtil.setFieldValue(
									((ExtendedSqlMapClient) sqlMapClient)
											.getDelegate(), "sqlExecutor",
											SqlExecutor.class, sqlExecutor);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public String getModelName(){
		return persistentClass.getSimpleName().toLowerCase();
	}
	
	public void delete(PK id) {
		this.getSqlMapClientTemplate().delete(getModelName()+".delete", id);
	}

	public T get(PK id) {
		return (T)this.getSqlMapClientTemplate().queryForObject(getModelName()+".getByPk", id);
	}

	public int getCount(Map map) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject(getModelName()+".getCount", map);
	}

	public List getList(Map map) {
		int currentpage=0,pagesize=0;
		String start =map.get("start")!=null?map.get("start").toString():null;
		String limit =map.get("limit")!=null?map.get("limit").toString():null;
		//针对 有些地方没有用BaseAction  中 pageTool() 分页方法的 分页
		if(start!=null &&  limit!=null  && !"".equals(start) && !"".equals(limit)){
			currentpage=Integer.parseInt(start)==0?1:Integer.parseInt(start);
			pagesize=Integer.parseInt(limit);
		}
		if(currentpage==0 || pagesize==0){//表示没有进行分页方法调用，查询所有记录
		    return this.getSqlMapClientTemplate().queryForList(getModelName()+".getList",map);
		}
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getList",map,(currentpage-1)*pagesize,pagesize);
	}
	
	public PageVo getPageList(Map map){
		
		int currentpage=0,pagesize=0;
		String start =map.get("start")!=null?map.get("start").toString():null;
		String limit =map.get("limit")!=null?map.get("limit").toString():null;
		if(start!=null &&  limit!=null  && !"".equals(start) && !"".equals(limit)){
			currentpage=Integer.parseInt(start)==0?1:Integer.parseInt(start);
			pagesize=Integer.parseInt(limit);
		}
		PageVo pageVo = new PageVo();
		
		List list = getList(map);
		int count = getCount(map);
		
		PageUtil page = new PageUtil();
		page.setCurPage(currentpage);
		page.setPageSize(pagesize);
		page.setTotalRow(count);
		String pageString = page.getNumberPageBar();
		
		pageVo.setCount(count);
		pageVo.setList(list);
		pageVo.setPageString(pageString);
		
		return pageVo;
	}
	
	public String insert(T t) {
		Object obj = this.getSqlMapClientTemplate().insert(getModelName()+".insert", t);
		if(obj instanceof String){
			return obj.toString();
		}else{
			return null;
		}
	}

	public void update(T t) {
		this.getSqlMapClientTemplate().update(getModelName()+".update", t);
	}
	
}
