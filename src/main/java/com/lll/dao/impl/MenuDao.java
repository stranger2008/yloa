package com.lll.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.lll.dao.IMenuDao;
import com.lll.model.Menu;

@Repository
public class MenuDao extends GenericDao<Menu,String> implements IMenuDao {
	
	public MenuDao() {
		super(Menu.class);
	}

	public List getThreeMenuListByOneMenu(Map map) {
		return this.getSqlMapClientTemplate().queryForList("menu.getThreeMenuListByOneMenu",map);
	}
	
	public void updateSort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("menu.updateSort", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
}
