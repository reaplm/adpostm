<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/WEB-INF/views/jspinc/header.jsp" />
		<div  id="pg-login">
			<div class="container">
				<jsp:include page="/WEB-INF/views/jspinc/logo.jsp" />
				<form id="form-login" action="/adpostm/login" method="post" class="form-horizontal">
						<div class="form-group">
							<input type="text" name="username" id="username" 
								class = "form-control" placeholder="Username" />
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password"
								class = "form-control" placeholder="Password" />

						</div>
						<div class="form-group">
							<button class="btn btn-primary" onclick="submitLogin()" >SUBMIT</button>
						</div>
						<div class="form-group">
							
								<a href="#">Forgot Password?</a>
							
								<a href="/adpostm/register" class="float-right">Register</a>
							
							
						</div>
				</form>
				<div>${msg}</div>
			</div>
		</div>
	</body>
</html>