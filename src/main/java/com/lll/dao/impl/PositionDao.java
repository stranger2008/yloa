/*
 * ISConsole Copyright 2013 lilianglin . 
 * All rights reserved.
 * Package:com.lll.dao.impl
 * FileName: PositionDao.java 
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
import com.lll.dao.IPositionDao;
import com.lll.model.Position;

/**
 * @function 功能  职位dao层业务接口实现类
 * @author 创建人 李良林
 * @date 创建日期 Sat Jun 08 22:39:26 CST 2013
 */
@Repository
public class PositionDao extends GenericDao<Position,String> implements IPositionDao {
	
	public PositionDao() {
		super(Position.class);
	}
	
	public void updateSort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("position.updateSort", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
}

