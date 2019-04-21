<%@page import="cn.edu.swu.javaweb.mvc.User" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户</title>
<link href="imgs/favicon.ico" rel="shortcut icon" />
</head>
<body>
	<% 
		List<User> stus = (List<User>)request.getAttribute("users");
	if(stus == null){
		System.out.println("stus is null");
	}else{
		System.out.println("stus is not null");		
	}
	%>

	<table border="1" cellpadding="10" cellspacing="0">
	
		<tr>
			<th>id</th>
			<th>username</th>
			<th>password</th>
			<th>Delete</th>
		</tr>
		
		<% 
		//if(stus != null)
			for(User user: stus) {
		%>
				<tr>
					<td><%= user.getId() %></td>
					<td><%= user.getUsername() %></td>
					<td><%= user.getPassword() %></td>
					<td><a href="deleteUser?id=<%=user.getId() %>">Delete</a></td>
				</tr>
		<%		
			}
		%>
	
	</table>
	
</body>
</html>