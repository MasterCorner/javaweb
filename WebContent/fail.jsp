<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fail</title>
</head>
<body>
<b2>
fail
<br><br>
登录失败，此页面由 转发 跳转而来，是一个jsp文件。

<br><br>
Sorry
<%=request.getParameter("username") %>
</b2>
</body>
</html>