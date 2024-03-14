<%@page import="com.rays.pro4.controller.ProductCtl"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<meta  charset="utf-8"">
<title>Product</title>
</head>
<body>
<jsp:useBean id="bean" class="com.rays.pro4.Bean.ProductBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>
	<center>

		<form action="<%=ORSView.PRODUCT_CTL%>" method="post">
		<div align="center">
				<h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update Product </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Product </font></th>
					</tr>
					<%
					}
					%>
				</h1>

				<h3>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>
			
	<input type="hidden" name="id" value="<%=bean.getId()%>">
	<table>
	<tr>
					<th align="left">ProductPrice <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="productPrice"
						placeholder="Enter Product Price" size="25"
						value="<%=(DataUtility.getStringData(bean.getProductPrice()).equals("0") ? "" : DataUtility.getStringData(bean.getProductPrice()))%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("productPrice", request)%></font></td>

				</tr>
				
				<tr>
					<th align="left">ProductName <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="productName"
						placeholder="Enter Product Name" size="25"
						value="<%=DataUtility.getStringData(bean.getProductName())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("productName", request)%></font></td>

				</tr>
				
				
				
				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=ProductCtl.OP_UPDATE%>"> &nbsp;
						 &nbsp; <input type="submit" name="operation"
						value="<%=ProductCtl.OP_CANCEL%>"></td>
 
					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=ProductCtl.OP_SAVE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=ProductCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>
	
	</table>
	</form>
	</center>

</body>
</html>