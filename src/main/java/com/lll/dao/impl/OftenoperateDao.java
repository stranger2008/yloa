/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: OftenoperateDao.java 
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
import com.lll.dao.IOftenoperateDao;
import com.lll.model.Oftenoperate;

/**
 * @function 功能  常用操作dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Wed Jun 19 23:09:46 CST 2013
 */
@Repository
public class OftenoperateDao extends GenericDao<Oftenoperate,String> implements IOftenoperateDao {
	
	public OftenoperateDao() {
		super(Oftenoperate.class);
	}
	public void updateSort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("oftenoperate.updateSort", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}

