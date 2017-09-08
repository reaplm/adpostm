<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/header.jsp"></jsp:include>
<div id="pg-dashboard">
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
				CONTENT
			</div>
		</div>
		<div class="row">
			<jsp:include page="/WEB-INF/views/jspinc/footer.jsp" />
		</div>
	</div>
</div>