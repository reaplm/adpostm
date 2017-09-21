<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div class="" id="admin-menu">
		
		<div class="navbar navbar-expand navbar-dark bg-primary" role="navigation">
			<a class="navbar-brand" href="/adpostm/home">ADPOST</a>
			<div class="navbar-collapse collapse">
				<ul  class="navbar-nav small pt-1">
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
		</div>
	</div>