/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: OrganizeDao.java 
 */
package com.lll.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.lll.dao.IOrganizeDao;
import com.lll.model.Organize;

/**
 * @function 功能  部门dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Sun Jun 09 12:09:55 CST 2013
 */
@Repository
public class OrganizeDao extends GenericDao<Organize,String> implements IOrganizeDao {
	
	public OrganizeDao() {
		super(Organize.class);
	}
	public void updateSort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("organize.updateSort", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}

