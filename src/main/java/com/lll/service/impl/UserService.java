package com.lll.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lll.dao.IUserDao;
import com.lll.model.User;
import com.lll.service.IUserService;

@Service
public class UserService extends GenericService<User,String> implements IUserService {
	
	IUserDao userDao;

	@Autowired
	public UserService(IUserDao userDao) {
		super(userDao);
		this.userDao = userDao;
	}
	
}
