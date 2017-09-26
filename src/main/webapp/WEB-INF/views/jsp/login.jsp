<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp" />
		<div  id="pg-login">
			<div class="container">
				<jsp:include page="/WEB-INF/views/jspinc/logo.jsp" />
				<form id="form-login" action="/adpostm/login" method="post" class="form-horizontal">
						<div class="form-group has-danger">	
								<input type="text" name="username" id="username" 
									class = "form-control" placeholder="Username" />
						<div class="error-div">
							<label id="username-error" class="form-control-danger" for="username">
							</label>
						</div>
						</div>
						<div class="form-group has-danger">
							<input type="password" name="password" id="password"
								class = "form-control" placeholder="Password" />
							<div class="error-div">
								<label id="password-error" class="form-control-danger" 
								for="password"></label>
							</div>
						</div>
						<div class="form-group">
							<button class="btn btn-primary" onclick="submitLogin()" role="button">SUBMIT</button>
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