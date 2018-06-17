package org.yueqian.service.impl;

import org.yueqian.bean.User;
import org.yueqian.dao.UserDao;
import org.yueqian.dao.impl.UserDaoImpl;
import org.yueqian.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	
	@Override
	public User findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

}
