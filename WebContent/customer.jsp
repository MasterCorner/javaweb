<%@page import="java.util.List"%>
<%@page import="cn.edu.swu.mvcapp.domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="query.do" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"/></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="Query"/></td>
				<td><a href="">Add New Customer</a></td>
			</tr>
		</table>	
	</form>
	
	<br><br>
	
	<%
		List<Customer> customers = (List<Customer>)request.getAttribute("customers");
		if(customers != null && customers.size() > 0){
	%>
	
	<hr>
	<br><br>
		
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>CustomerName</th>
				<th>Address</th>
				<th>Phone</th>
				<th>UPDATE\DELETE</th>
			</tr>
	
			<%
					for(Customer customer: customers){
			%>
	
			<tr>
				<td><%= customer.getId() %></td>
				<td><%= customer.getName() %></td>
				<td><%= customer.getAddress() %></td>
				<td><%= customer.getPhone() %></td>
				<td>
					<a href="">UPDATE</a>
					<a href="">DELETE</a>
			</tr>
			<%
				}
			%>		
		
		</table>
	<%
		}
	%>	
</body>
</html>