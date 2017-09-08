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
		<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery.validate.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<title>Login</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/jspinc/logo.jsp" />
		<div  id="pg-register">
			<div class="container">
				<form id="form-register" action="/adpostm/register" method="post" class="form-horizontal">
						<div class="form-group clearfix">
							<div class="col-sm-6">
								<input type="text" name="fname"
								class = "form-control" placeholder="First Name" />
							</div>
							<div class="col-sm-6">
								<input type="text" name="lname"
								class = "form-control" placeholder="Last Name" />
							</div>
						</div>
						<div class="form-group">
							<input type="text" name="email"
								class = "form-control" placeholder="Email" />
						</div>
						<div class="form-group clearfix">
							<div class="col-sm-6">
								<input type="password" name="password"
								class = "form-control" placeholder="Password" />
							</div>
							<div class="col-sm-6">
								<input type="password" name="passRepeat"
								class = "form-control" placeholder="Re-type Password" />
							</div>
						</div>
					
						<div class="form-group">
							<button class="btn btn-primary" onclick="SubmitRegistration()" >SUBMIT</button>
						</div>
				</form>
				<div>${msg}</div>
			</div>
		</div>
	</body>
</html>