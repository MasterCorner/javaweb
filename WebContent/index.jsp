<%@ page contentType = "text/html; charset=utf-8" session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8" />
<title>WRM's Demo App</title>
</head>
	<body>
		<center>
		<div style="background-color:#ddd;padding:30%;margin-top:5em">
			<form action="loginServlet" method="post">
	
				user: <input type="text" name="user"/>
				password: <input type="password" name="pass"/>
		
				<input type="submit" value="Submit"/>
	
			</form>
		</div>
		</center>
	</body>
</html>
