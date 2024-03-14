package com.rays.proj4.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Model.BankModel;

public class BankTest {

	public static void main(String[] args) throws Exception {
		
		//testAdd();
		//testUpdate();
		//testDelete();
		testSearch();
		//testFindByPK();
	}

	private static void testFindByPK() throws Exception{

		BankBean bean = new BankBean();
		long pk = 1;
		BankModel model = new BankModel();
		
		bean = model.findByPK(pk);
		
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getAccountNo());
	}

	private static void testSearch() throws Exception {
		
		BankBean bean = new BankBean();
		BankModel model = new BankModel();
		List list =  new ArrayList();
		
		list = model.search(bean, 0, 0);
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			bean = (BankBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getAccountNo());
			System.out.println(bean.getName());
		}
		
	}

	private static void testDelete() throws Exception {
		
		BankBean bean = new BankBean();
		BankModel model = new BankModel();
		
		bean.setId(2);
		model.delete(bean);
	}

	private static void testUpdate() throws Exception {

		BankBean bean = new BankBean();
		BankModel model = new BankModel();
		
		bean.setName("Shiva");
		bean.setAccountNo(45564743);
		bean.setId(1);
		model.update(bean);
		
		
	}

	private static void testAdd() throws Exception {

		BankBean bean = new BankBean();
		BankModel model = new BankModel();
		
		bean.setName("Hritika");
		bean.setAccountNo(1234567);
		bean.setId(2);
		model.add(bean);
		
	}
	
}
