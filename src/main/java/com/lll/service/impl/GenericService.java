package com.lll.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lll.common.page.PageVo;
import com.lll.dao.IGenericDao;
import com.lll.service.IGenericService;

@Service
public class GenericService<T,PK> implements IGenericService<T,PK>{
	
	IGenericDao<T,PK> genericDao;
	
	public GenericService() {}

    public GenericService(IGenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }
	
	public void delete(PK id) {
		genericDao.delete(id);
	}

	public T get(PK id) {
		return (T)genericDao.get(id);
	}

	public int getCount(Map map) {
		return genericDao.getCount(map);
	}

	public List getList(Map map) {
		return genericDao.getList(map);
	}

	public String insert(T t) {
		return genericDao.insert(t);
	}

	public void update(T t) {
		genericDao.update(t);
	}
	
	public PageVo getPageList(Map map){
		return genericDao.getPageList(map);
	}
}
