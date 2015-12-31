package com.lll.dao.impl;

import org.springframework.stereotype.Repository;

import com.lll.dao.IUserDao;
import com.lll.model.User;

@Repository
public class UserDao extends GenericDao<User,String> implements IUserDao {
	
	public UserDao() {
		super(User.class);
	}
	
}
