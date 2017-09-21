<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div class="" id="top-menu">
		<!--  
		<a class="navbar-brand" href="/adpostm/home" >
			<img src="<%=request.getContextPath()%>/resources/images/logo.png" alt="home" title="adpost home" 
				width="50" height="50" />
		</a>
		-->
		<ul  class="nav justify-content-end">
			<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
				<li class="nav-item">
					<a href="/adpostm/login" class="nav-link">sign in</a>
				</li>
				<li class="nav-item">
					<a href="/adpostm/register" class="nav-link">register</a>
				</li>
			</sec:authorize>
			
			<sec:authorize access="hasAnyRole('ADMIN','USER')">
					<li class="nav-item">
						<a href="/adpostm/home" class="nav-link">home</a>
					</li>
					<li class="nav-item">
						<a href="#" class="nav-link">post ad</a>
					</li>
					<li class="nav-item">
						<a href="/adpostm/admin" class="nav-link">admin</a>
					</li>
					<li class="nav-item">
						<a href="/adpostm/logout" class="nav-link">logout</a>
					</li>
					
			</sec:authorize>
		</ul>
	</div>