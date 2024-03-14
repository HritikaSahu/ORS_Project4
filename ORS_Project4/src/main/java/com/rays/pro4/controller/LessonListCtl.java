package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.LessonBean;
import com.rays.pro4.Model.LessonModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "LessonListCtl", urlPatterns = { "/ctl/LessonListCtl" })
public class LessonListCtl extends BaseCtl{


		
			 @Override
				protected BaseBean populateBean(HttpServletRequest request) {
					
				    LessonBean bean = new LessonBean();
					bean.setId(DataUtility.getLong(request.getParameter("id")));
					bean.setLessonNo(DataUtility.getInt(request.getParameter("lessonNo")));
					
					bean.setLessonName(DataUtility.getString(request.getParameter("lessonName")));
					 
					return bean;
				}
				@Override
				protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

					List list = null;
					
					int pageNo = 1;
					int pageSize = 10;
					
					LessonBean bean = (LessonBean) populateBean(request);
					String op = DataUtility.getString(request.getParameter("operation"));
					LessonModel model = new LessonModel();
					 
				 try {
					list =  model.search(bean, pageNo, pageSize);
					
					ServletUtility.setList(list, request);
					ServletUtility.setPageNo(pageNo, request);
					ServletUtility.setPageSize(pageSize, request);
					ServletUtility.setBean(bean, request);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
					return;
				}
				@Override
				protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					List list;
					
					System.out.println("search dop post");
					int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
					int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
					
					LessonBean bean = (LessonBean) populateBean(request);
					
					String op = DataUtility.getString(request.getParameter("operation"));
					System.out.println("operation");
					System.out.println(op);
					String[] ids = request.getParameterValues("ids");
					LessonModel model = new LessonModel();
					if (OP_SEARCH.equalsIgnoreCase(op)) {
						System.out.println("inner search");
						pageNo = 1;
					}else if (OP_NEW.equalsIgnoreCase(op)) {
						ServletUtility.redirect(ORSView.LESSON_CTL, request, response);
						return;
					}else if (OP_DELETE.equalsIgnoreCase(op)) {
						pageNo = 1;
						if (ids != null && ids.length > 0) {
							LessonBean deletebean = new LessonBean();
							for (String id : ids) {
								deletebean.setId(DataUtility.getInt(id));
								try {
									model.delete(deletebean);
								
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								ServletUtility.setSuccessMessage("Lesson is Deleted Successfully", request);
							}
						} else {
							ServletUtility.setErrorMessage("Select at least one record", request);
						}
					}
				 try {
					 
					list = model.search(bean, pageNo, pageSize);
					
					System.out.println("step");
					
					if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
					 	ServletUtility.setErrorMessage("No record found ", request);
						}
			
					 ServletUtility.setList(list, request);
						ServletUtility.setPageNo(pageNo, request);
						ServletUtility.setPageSize(pageSize, request);
						ServletUtility.setBean(bean, request);
						
						ServletUtility.forward(getView(), request, response);
					
				 } catch (Exception e) {
						ServletUtility.handleException(e, request, response);
						return;
					
				 }
					
				}
			@Override
			protected String getView() {
				// TODO Auto-generated method stub
				return ORSView.LESSON_LIST_VIEW;
			}

		}

		
		


	

