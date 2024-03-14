package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.LessonBean;
import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Util.JDBCDataSource;

public class LessonModel {

	public long nextPK() throws Exception {

		String sql = "SELECT MAX(ID) FROM ST_LESSON";

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			throw new Exception("Exception: Exception in getting pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}

	public long add(LessonBean bean) throws Exception {
		long pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		pk = nextPK();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_LESSON VALUES(?,?,?)");

		ps.setLong(1, pk);
		ps.setInt(2, bean.getLessonNo());
		ps.setString(3, bean.getLessonName());
		

		int i = ps.executeUpdate();

		System.out.println("Data Added" + i);
		return pk;
	}

	public void update(LessonBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("UPDATE ST_LESSON SET LESSON_NAME = ?, LESSON_NO = ? WHERE ID = ?");

		ps.setString(1, bean.getLessonName());
		ps.setInt(2, bean.getLessonNo());
		ps.setLong(3, bean.getId());

		int i = ps.executeUpdate();

		System.out.println("Data Updated" + i);

	}

	public void delete(LessonBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_LESSON WHERE ID = ?");

		ps.setLong(1, bean.getId());

		int i = ps.executeUpdate();

		System.out.println("Data Deleted" + i);
	}

	public List search(LessonBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_LESSON WHERE 1=1");

		if (bean != null) {
			if (bean.getLessonName() != null && bean.getLessonName().length() > 0) {
				sql.append(" AND LESSON_NAME LIKE '" + bean.getLessonName() + "%'");
			}
			if (bean.getLessonNo() > 0) {
				sql.append(" AND LESSON_NO =" + bean.getLessonNo());
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		List list = new ArrayList();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		System.out.println(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bean = new LessonBean();
			bean.setId(rs.getLong(1));
			bean.setLessonNo(rs.getInt(2));
			bean.setLessonName(rs.getString(3));

			list.add(bean);
		}
		return list;
	}

	public LessonBean findByPK(long pk) throws Exception {
		
		LessonBean bean = null;
		
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_LESSON WHERE ID = ?");
		
		ps.setLong(1,pk);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			bean = new LessonBean();
			bean.setId(rs.getLong(1));	
			bean.setLessonNo(rs.getInt(2));
			bean.setLessonName(rs.getString(3));
			
		}
		return bean;
	}
}
