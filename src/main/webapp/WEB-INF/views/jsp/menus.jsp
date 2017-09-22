<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<div id="pg-menus" class="">
	<div class="container pg-admin">
		<div class="row">
				<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-sm-3 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 content nopadding">
				<div class="card-header">
					<ul class="nav nav-tabs card-header-tabs" role="tablist">
						<li class="nav-item"><a href="#category" data-toggle="tab"
							class="nav-link active" role="tab">category</a></li>
						<li class="nav-item"><a href="#admin" data-toggle="tab"
							class="nav-link" role="tab">admin</a></li>
					</ul>
					</div>
					<div class="tab-content pl-2 pr-2">
						<div class="tab-pane active" id="category" role="tabpanel">
							<jsp:include page="/WEB-INF/views/jsp/category.jsp"></jsp:include>
						</div>
						<div class="tab-pane" id="admin" role="tabpanel">
							<jsp:include page="/WEB-INF/views/jsp/admin.jsp"></jsp:include>
						</div>
					</div>
			</div>
		</div>
		<br />
		<div class="row">
			 <jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/jsp/editMenuForm.jsp"></jsp:include>
</div>