<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>success</title>
<link href="imgs/favicon.ico" rel="shortcut icon" />
</head>
<body>
<h2>
<br><br>
登陆成功，此页面由 转发 跳转而来，这是一个jsp文件。

<br><br>
Hello
<%=request.getParameter("username") %>
</h2>
<br>
<a href="ListAllStudents">List All Students</a>
<br><br>
<a href="./customer.jsp">List All Customer</a>
</body>
</html>