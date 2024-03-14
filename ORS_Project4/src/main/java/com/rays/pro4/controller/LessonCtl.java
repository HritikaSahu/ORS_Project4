package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.LessonBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.LessonModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "LessonCtl", urlPatterns = { "/ctl/LessonCtl" })

public class LessonCtl extends BaseCtl{


				@Override
				protected boolean validate(HttpServletRequest request) {
					
					
					boolean pass = true;
					
		if(DataValidator.isNull(request.getParameter("lessonName"))) {
		   request.setAttribute("lessonName", PropertyReader.getValue("error.require","lessonName"));
			pass = false;
			
		}else if(!DataValidator.isName(request.getParameter("lessonName"))) {
		  request.setAttribute("lessonName","Lesson Name must contains alphabet only");
		pass = false;
					}
				
		if(DataValidator.isNull(request.getParameter("lessonNo"))) {
		  request.setAttribute("lessonNo", PropertyReader.getValue("error.require", "lessonNo"));
		  pass = false;
				}
				
				
	   else if(!DataValidator.isInteger(request.getParameter("lessonNo"))) {
		 request.setAttribute("lessonNo","Lesson No must contains number only");
				  
				  pass = false; }
				 
					 return pass;
				}
				 
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

					 String op = DataUtility.getString(request.getParameter("operation"));
					 LessonModel model = new LessonModel();
					 
					long id = DataUtility.getLong(request.getParameter("id"));
					
					if(id > 0 || op != null) {
						LessonBean bean;
						try {
						bean = model.findByPK(id)
			;
						System.out.println(bean);
						
						ServletUtility.setBean(bean, request);
						}catch (Exception e){
							ServletUtility.handleException(e, request, response);
							return;
						}
					}
					 ServletUtility.forward(getView(), request, response);
				}
				 @Override
				protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

					 String op = DataUtility.getString(request.getParameter("operation"));
					 long id = DataUtility.getLong(request.getParameter("id"));
						
						LessonModel model = new LessonModel();
						
						if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
							LessonBean bean = (LessonBean) populateBean(request);
							try {
								if (id > 0) {
				 
									model.update(bean);
									ServletUtility.setBean(bean, request);
									ServletUtility.setSuccessMessage("Lesson is successfully Updated", request);

								} else {
								long pk =	model.add(bean);

									ServletUtility.setSuccessMessage("Lesson is successfully Added", request);
									bean.setId(pk);
								}

							} catch (ApplicationException e) {
								
								ServletUtility.handleException(e, request, response);
								return;
							} catch (DuplicateRecordException e) {
								ServletUtility.setBean(bean, request);
								ServletUtility.setErrorMessage("Lesson is already exists", request);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (OP_DELETE.equalsIgnoreCase(op)) {

							LessonBean bean = (LessonBean) populateBean(request);
							try {
								model.delete(bean);
								ServletUtility.redirect(ORSView.LESSON_CTL, request, response);
								return;
							} catch ( Exception e) {
								
								ServletUtility.handleException(e, request, response);
								return;
							}

						} else if (OP_CANCEL.equalsIgnoreCase(op)) {

							ServletUtility.redirect(ORSView.LESSON_LIST_CTL, request, response);
							return;
						}
					


					
					 ServletUtility.forward(getView(), request, response);
				}
				@Override
				protected String getView() {

					return ORSView.LESSON_VIEW;
				}

		}


	
	

