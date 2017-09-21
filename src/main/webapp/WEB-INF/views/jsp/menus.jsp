<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/header.jsp"></jsp:include>
<div id="pg-menus" class="pg-admin">
	<div class="container">
		<div class="row">
				<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-sm-3 nopadding">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9">
				<div class="row">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a href="#category" data-toggle="tab"
							class="nav-link active" role="tab">category</a></li>
						<li class="nav-item"><a href="#admin" data-toggle="tab"
							class="nav-link" role="tab">admin</a></li>

					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="category" role="tabpanel">
							<jsp:include page="/WEB-INF/views/jsp/category.jsp"></jsp:include>
						</div>
						<div class="tab-pane" id="admin" role="tabpanel">
							<jsp:include page="/WEB-INF/views/jsp/admin.jsp"></jsp:include>
						</div>
					</div>
				</div>
				<div class="row"></div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/jsp/editMenuForm.jsp"></jsp:include>
</div>