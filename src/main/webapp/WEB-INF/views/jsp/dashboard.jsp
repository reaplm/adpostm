<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<div id="pg-dashboard">
	<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
	<div class="container pg-admin">
		<div class="row">
			<div class="col-sm-3 col-lg-3 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 col-lg-9 content content-fluid">
				CONTENT
			</div>
		</div>
		<br />
	</div>
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp" />
</div>