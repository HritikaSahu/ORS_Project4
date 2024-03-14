package com.rays.proj4.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.LessonBean;
import com.rays.pro4.Model.LessonModel;

public class LessonTest {

	public static void main(String[] args) throws Exception {
		
		//testAdd();
		//testUpdate();
		testDelete();
		//testSearch();
		
	}

	
	private static void testSearch() throws Exception {
		
		LessonBean bean = new LessonBean();
		LessonModel model = new LessonModel();
		List list =  new ArrayList();
		
		list = model.search(bean, 0, 0);
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			bean = (LessonBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getLessonNo());
			System.out.println(bean.getLessonName());
		}
		
	}

	private static void testDelete() throws Exception {
		
		LessonBean bean = new LessonBean();
		LessonModel model = new LessonModel();
		
		bean.setId(2);
		model.delete(bean);
	}

	private static void testUpdate() throws Exception {

		LessonBean bean = new LessonBean();
		LessonModel model = new LessonModel();
		
		bean.setLessonName("APJ Abdul Kalam");
		bean.setLessonNo(2);
		bean.setId(2);
		model.update(bean);
		
		
	}

	private static void testAdd() throws Exception {

		LessonBean bean = new LessonBean();
		LessonModel model = new LessonModel();
		
		bean.setId(3);
		bean.setLessonNo(2);
		bean.setLessonName("Sunrays");
		model.add(bean);
		
	}
	

	}
	

