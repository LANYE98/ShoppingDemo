package org.yueqian.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf-8", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 查询通用方法
	public List<T> executQuery(String sql, Object... obj) {
		// 用于存储rs返回的结果
		List<T> list = new ArrayList<T>();
		try {
			// 通过getConnecttion获得链接字符串，并创建了一个prepareStatement
			pst = getConnection().prepareStatement(sql);
			if (obj != null) {
				// 通过循环对占位符赋值
				for (int i = 0; i < obj.length; i++) {
					// 位置是从1开始，数组的值从下标开始
					pst.setObject(i + 1, obj[i]);
				}
			}
			// 通过prepareStatement对象的executeQuery执行sql操作并返回一个结果集
			rs = pst.executeQuery();
			// 判断rs是否有下一个值
			while (rs.next()) {
				// 如果有值，将结果集交个getEntity去获取到具体的对象，添加到集合
				list.add(getEntity(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			getClose(conn, pst, rs);
		}
		return list;
	}

	public int Query(String sql, Object... obj) {
		int num = 0;
		try {
			// 通过getConnecttion获得链接字符串，并创建了一个prepareStatement
			pst = getConnection().prepareStatement(sql);
			if (obj != null) {
				// 通过循环对占位符赋值
				for (int i = 0; i < obj.length; i++) {
					// 位置是从1开始，数组的值从下标开始
					pst.setObject(i + 1, obj[i]);
				}
			}
			// 通过prepareStatement对象的executeQuery执行sql操作并返回一个结果集
			rs = pst.executeQuery();
			// 判断rs是否有下一个值
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			getClose(conn, pst, rs);
		}
		return num;
	}

	public boolean executUpdate(String sql, Object... obj) {
		boolean flage = false;
		try {
			// 通过getConnecttion获得链接字符串，并创建了一个prepareStatement
			pst = getConnection().prepareStatement(sql);
			if (obj != null) {
				// 通过循环对占位符赋值
				for (int i = 0; i < obj.length; i++) {
					// 位置是从1开始，数组的值从下标开始
					pst.setObject(i + 1, obj[i]);
				}
			}
			int num = pst.executeUpdate();
			// 判断rs是否有下一个值
			if (num > 0) {
				flage = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			getClose(conn, pst, rs);
		}
		return flage;
	}

	public void getClose(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract T getEntity(ResultSet rs);
}
