package org.yueqian.dao;

import org.yueqian.bean.User;

public interface UserDao {
	
	public User findByLoginName(String loginName);

}
