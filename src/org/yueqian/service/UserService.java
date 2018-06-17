package org.yueqian.service;

import org.yueqian.bean.User;

public interface UserService {

	public User findByLoginName(String loginName);
	
}
