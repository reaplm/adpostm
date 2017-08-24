<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="<%=request.getContextPath()%>/resources/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/resources/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath()%>/resources/js/tether-1.3.3/dist/js/tether.min.js"></script>		
		<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-3.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap/js/bootstrap.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Login</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/jspinc/logo.jsp" />
		<div id="pg-login">
			<form action="#" method="post">
				<div class="form-row">
					<input type="text" name="username" id="username" 
						class = "textbox" placeholder="Username" />
				</div>
				<div class="form-row">
					<input type="password" name="password" id="password"
						class = "textbox" placeholder="password" />
				</div>
				<div class="form-row">
					<button class="button" >SUBMIT</button>
				</div>
				<div class="form-row">
					<a href="#">Forgot Password?</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Register</a>
				</div>
			</form>
		</div>
	</body>
</html>