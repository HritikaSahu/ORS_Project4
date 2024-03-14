package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.ProductModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ProductCtl", urlPatterns = { "/ctl/ProductCtl" })
public class ProductCtl extends BaseCtl {

			@Override
			protected boolean validate(HttpServletRequest request) {
				boolean pass = true;
				
				if(DataValidator.isNull(request.getParameter("productName"))) {
					request.setAttribute("productName", PropertyReader.getValue("error.require","productName"));
					pass = false;
				}else if(!DataValidator.isName(request.getParameter("productName"))) {
					request.setAttribute("productName","Product Name must contains alphabet only");
					pass = false;
				}
				
				if(DataValidator.isNull(request.getParameter("productPrice"))) {
					request.setAttribute("productPrice", PropertyReader.getValue("error.require","productPrice"));
					pass = false;
			}
			
			/*
			 * else if(!DataValidator.isInteger(request.getParameter("accounto"))) {
			 * request.setAttribute("accountNo","accountNo must contains number only");
			 * 
			 * pass = false; }
			 */
				 return pass;
			}
			 
			 @Override
			protected BaseBean populateBean(HttpServletRequest request) {
				ProductBean bean = new ProductBean();
				bean.setId(DataUtility.getLong(request.getParameter("id")));
				bean.setProductPrice(DataUtility.getInt(request.getParameter("productPrice")));
				bean.setProductName(DataUtility.getString(request.getParameter("productName")));
				
				 return bean;
			}
			 
			 @Override
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				 String op = DataUtility.getString(request.getParameter("operation"));
				 ProductModel model = new ProductModel();
				 
				long id = DataUtility.getLong(request.getParameter("id"));
				
				if(id > 0 || op != null) {
					ProductBean bean;
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
					
					ProductModel model = new ProductModel();
					
					if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
						ProductBean bean = (ProductBean) populateBean(request);
						try {
							if (id > 0) {
			 
								model.update(bean);
								ServletUtility.setBean(bean, request);
								ServletUtility.setSuccessMessage("Product is successfully Updated", request);

							} else {
							long pk =	model.add(bean);

								ServletUtility.setSuccessMessage("Product is successfully Added", request);
								bean.setId(pk);
							}

						} catch (ApplicationException e) {
							
							ServletUtility.handleException(e, request, response);
							return;
						} catch (DuplicateRecordException e) {
							ServletUtility.setBean(bean, request);
							ServletUtility.setErrorMessage("Product is already exists", request);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (OP_DELETE.equalsIgnoreCase(op)) {

						ProductBean bean = (ProductBean) populateBean(request);
						try {
							model.delete(bean);
							ServletUtility.redirect(ORSView.PRODUCT_CTL, request, response);
							return;
						} catch ( Exception e) {
							
							ServletUtility.handleException(e, request, response);
							return;
						}

					} else if (OP_CANCEL.equalsIgnoreCase(op)) {

						ServletUtility.redirect(ORSView.PRODUCT_LIST_CTL, request, response);
						return;
					}
				


				
				 ServletUtility.forward(getView(), request, response);
			}
			@Override
			protected String getView() {

				return ORSView.PRODUCT_VIEW;
			}

	}

