<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/header.jsp"></jsp:include>
<div id="pg-menus">
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<jsp:include page="/WEB-INF/views/jspinc/logo.jsp"></jsp:include>
			</div>
			<div class="col-sm-9">
				<jsp:include page="/WEB-INF/views/jspinc/topMenu.jsp"></jsp:include>		
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9">
				<div class="row">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item">
							<a href="#category" data-toggle="tab" 
								class="nav-link active" role="tab">category</a>
						</li>
						<li class="nav-item"><a href="#" class="nav-link">admin</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="category" role="tabpanel">
							<jsp:include page="/WEB-INF/views/jsp/category.jsp"></jsp:include>
						</div>
					</div>
				</div>
				<div class="row"></div>
			</div>
			</div>
	</div>
</div>