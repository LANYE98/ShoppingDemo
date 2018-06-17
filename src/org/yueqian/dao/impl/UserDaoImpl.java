package org.yueqian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.yueqian.bean.User;
import org.yueqian.dao.BaseDao;
import org.yueqian.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User findByLoginName(String loginName) {
		String sql = "select * from user where login_name=?";
		List<User> list = executQuery(sql, loginName);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public User getEntity(ResultSet rs) {
		User user = new User();
		try {
			user.setId(rs.getInt("id"));
			user.setLoginName(rs.getString("login_name"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getInt("sex"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			user.setRole(rs.getInt("role"));
			user.setCreateDate(rs.getDate("create_date"));
			user.setDisabled(rs.getInt("disabled"));
			user.setActive(rs.getString("active"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
