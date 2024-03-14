package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Util.JDBCDataSource;


public class BankModel {

	public long nextPK() throws Exception {
		
		String sql = "SELECT MAX(ID) FROM ST_BANK";
		
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
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}
	
	public long add(BankBean bean) throws Exception {
		long pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		pk = nextPK();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_BANK VALUES(?,?,?)");
		
		ps.setLong(1, pk);
		ps.setString(2, bean.getName());
		ps.setInt(3, bean.getAccountNo());
		
		int i = ps.executeUpdate();
		
		System.out.println("Data Added" + i);
		return pk;
	}
	
	public void update(BankBean bean) throws Exception {
		
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE ST_BANK SET NAME = ?, ACCOUNT_NO = ? WHERE ID = ?");
		
		ps.setString(1, bean.getName());
		ps.setInt(2, bean.getAccountNo());
		ps.setLong(3, bean.getId());
		
		int i = ps.executeUpdate();
		
		System.out.println("Data Updated" + i);
		
	}
	
	public void delete(BankBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_BANK WHERE ID = ?");
		
		ps.setLong(1, bean.getId());
		
		int i = ps.executeUpdate();
		
		System.out.println("Data Deleted" + i);
	}
	
	public List search(BankBean bean,int pageNo,int pageSize) throws Exception {
		
		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_BANK WHERE 1=1");
		
		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() >0) {
				sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
			}
			if (bean.getAccountNo() > 0) {
				sql.append(" AND ACCOUNT_NO ="+ bean.getAccountNo());
			}
		}
		if (pageSize > 0) {
			pageNo=(pageNo-1)*pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		List list = new ArrayList();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		System.out.println(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bean = new BankBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAccountNo(rs.getInt(3));
			
			
			list.add(bean);
		}
		return list;
	}
	
	public BankBean findByPK(long pk) throws Exception {
		
		BankBean bean = null;
		
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_BANK WHERE ID = ?");
		
		ps.setLong(1,pk);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			bean = new BankBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAccountNo(rs.getInt(3));
			
		}
		return bean;
	}
}
