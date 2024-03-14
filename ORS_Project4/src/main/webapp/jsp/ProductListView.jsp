<%@page import="com.rays.pro4.Bean.ProductBean"%>
<%@page import="com.rays.pro4.controller.ProductListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<meta  charset="utf-8"">
<title>Product list</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>
<body>
<jsp:useBean id="bean" class="com.rays.pro4.Bean.ProductBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>
<form action = "<%=ORSView.PRODUCT_LIST_CTL%>" method = "post" >
<center>

			<div align="center">
				<h1 >Product List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>
			<%
			
			List list = ServletUtility.getList(request);
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			
			int index = (pageNo-1)*pageSize + 1;
			
			Iterator it = list.iterator();
			
			%>
			<table width="100%" align="center">
				<tr>
					<th></th>
					
					<td align="center"><label>Product Price</font> :
					</label> <input type="text" name="productPrice" placeholder="Enter Product Price"
						value="<%=ServletUtility.getParameter("productPrice", request)%>">


						<label>Product Name</font> :
					</label> <input type="text" name="productName" placeholder="Enter Product Name"
						value="<%=ServletUtility.getParameter("productName", request)%>">
						
						<input type="submit" name="operation"
						value="<%=ProductListCtl.OP_SEARCH%>"> &nbsp;  <input
						type="submit" name="operation" value="<%=ProductListCtl.OP_RESET%>"> 
 
					</td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: PALEGREEN">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>ProductPrice</th>
					<th>ProductName</th>
					
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
							bean = (ProductBean)it.next();
						
				%>
               
               

				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"
						></td>
					<td><%=index++%></td>
					<td><%=bean.getProductPrice()%></td>
					<td><%=bean.getProductName()%></td>
					<td><a href="ProductCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
				</table>
				<td><input type="submit" name="operation"
						value="<%=ProductListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=ProductListCtl.OP_NEW%>"></td>
			
			<%
				
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=ProductListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
				
			</center>

</form>
<%@include file="Footer.jsp"%>

</body>
</html>