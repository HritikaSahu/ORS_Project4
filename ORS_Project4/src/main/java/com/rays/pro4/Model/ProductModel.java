package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Util.JDBCDataSource;



public class ProductModel {


		public long nextPK() throws Exception {
			
			String sql = "SELECT MAX(ID) FROM ST_PRODUCT";
			
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
		
		public long add(ProductBean bean) throws Exception {
			long pk = 0;
			Connection conn = JDBCDataSource.getConnection();
			pk = nextPK();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_PRODUCT VALUES(?,?,?)");
			
			ps.setLong(1, pk);
			ps.setString(2, bean.getProductName());
			ps.setInt(3, bean.getProductPrice());
			
			int i = ps.executeUpdate();
			
			System.out.println("Data Added" + i);
			return pk;
		}
		
		public void update(ProductBean bean) throws Exception {
			
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE ST_PRODUCT SET PRODUCT_NAME = ?, PRODUCT_PRICE = ? WHERE ID = ?");
			
			ps.setString(1, bean.getProductName());
			ps.setInt(2, bean.getProductPrice());
			ps.setLong(3, bean.getId());
			
			int i = ps.executeUpdate();
			
			System.out.println("Data Updated" + i);
			
		}
		
		public void delete(ProductBean bean) throws Exception {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_PRODUCT WHERE ID = ?");
			
			ps.setLong(1, bean.getId());
			
			int i = ps.executeUpdate();
			
			System.out.println("Data Deleted" + i);
		}
		
		public List search(ProductBean bean,int pageNo,int pageSize) throws Exception {
			
			Connection conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_PRODUCT WHERE 1=1");
			
			if (bean != null) {
				if (bean.getProductName() != null && bean.getProductName().length() >0) {
					sql.append(" AND PRODUCT_NAME LIKE '" + bean.getProductName() + "%'");
				}
				if (bean.getProductPrice() > 0) {
					sql.append(" AND PRODUCT_PRICE ="+ bean.getProductPrice());
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
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductPrice(rs.getInt(3));
				
				
				list.add(bean);
			}
			return list;
		}
		
		public ProductBean findByPK(long pk) throws Exception {
			
			ProductBean bean = null;
			
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_PRODUCT WHERE ID = ?");
			
			ps.setLong(1,pk);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductPrice(rs.getInt(3));
				
			}
			return bean;
		}
	}

	
	

