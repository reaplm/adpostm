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
			<div class="col-sm-4 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-8 content content-fluid nopadding">
				USERS
			</div>
		</div>
		<br />
		<div class="row">
			 <jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/jsp/editMenuForm.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/jsp/newMenuForm.jsp"></jsp:include>
</div>