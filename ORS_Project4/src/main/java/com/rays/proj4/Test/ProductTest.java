package com.rays.proj4.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Model.ProductModel;

public class ProductTest {
	
	

		public static void main(String[] args) throws Exception {
			
			testAdd();
			//testUpdate();
			//testDelete();
			//testSearch();
			//testFindByPK();
		}

		private static void testFindByPK() throws Exception{

			ProductBean bean = new ProductBean();
			long pk = 1;
			ProductModel model = new ProductModel();
			
			bean = model.findByPK(pk);
			
			System.out.println(bean.getId());
			System.out.println(bean.getProductName());
			System.out.println(bean.getProductPrice());
		}

		private static void testSearch() throws Exception {
			
			ProductBean bean = new ProductBean();
			ProductModel model = new ProductModel();
			List list =  new ArrayList();
			
			list = model.search(bean, 0, 0);
			Iterator it = list.iterator();
			
			while (it.hasNext()) {
				bean = (ProductBean) it.next();
				
				System.out.println(bean.getId());
				System.out.println(bean.getProductPrice());
				System.out.println(bean.getProductName());
			}
			
		}

		private static void testDelete() throws Exception {
			
			ProductBean bean = new ProductBean();
			ProductModel model = new ProductModel();
			
			bean.setId(2);
			model.delete(bean);
		}

		private static void testUpdate() throws Exception {

			ProductBean bean = new ProductBean();
			ProductModel model = new ProductModel();
			
			bean.setProductName("LG WaterCooler");
			bean.setProductPrice(30000);
			bean.setId(2);
			model.update(bean);
			
			
		}

		private static void testAdd() throws Exception {

			ProductBean bean = new ProductBean();
			ProductModel model = new ProductModel();
			
			bean.setProductName("IPhone Apple");
			bean.setProductPrice(1000000);
			bean.setId(3);
			model.add(bean);
			
		}
		
	}



