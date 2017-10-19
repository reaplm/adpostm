<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="card text-capitalize" id="admin-menu">
	<div class="navbar">
		<div class="navbar navbar-toggleable-md justify-content-between"
			role="navigation" data-toggle="collapse">
			<a class="navbar-brand" href="/adpostm/home">ADPOST</a>
			<ul class="navbar-nav collapse" id="admin-menu-toggle">
				<sec:authorize access="hasAnyRole('ADMIN','USER')">
					<li class="nav-item"><a href="/adpostm/home" class="nav-link">home</a>
					</li>
					<li class="nav-item"><a href="#" class="nav-link">post ad</a>
					</li>
					<li class="nav-item"><a href="/adpostm/logout"
						class="nav-link">logout</a></li>

				</sec:authorize>
			</ul>
		</div>
		<button type="button" class="navbar-toggler hidden-lg-up navbar-toggler-right pt-3" 
			data-toggle="collapse" data-target="#admin-menu-toggle" 
			aria-controls="admin-menu-toggle"
			 aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon">&#9776;</span>
		</button>
	</div>
</div>