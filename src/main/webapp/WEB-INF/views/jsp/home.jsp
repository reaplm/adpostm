<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/WEB-INF/views/jspinc/header.jsp"></jsp:include>
<div id="pg-home">
	<div class="container">
		<div class="row">
			<jsp:include page="/WEB-INF/views/jspinc/topMenu.jsp" />
		</div>
		<%int colCount = 0; %>
		<c:if test="${fn: length(menus) > 0}">
			<c:forEach items="${menus}" var="menu">
				<%if(colCount == 0){//new row%>
					<div class="row">
				<%}%>
						<div class="col-sm-3" style="border: solid">
							<div class="float-left">
								<img src="<%=request.getContextPath()%>/resources/
									images/menu/${menu.getIcon()}" />
							</div>
							<a href="${menu.getUrl()}">${menu.getMenuName()}</a>
						</div>
				<%if(colCount == 4){ colCount = 0;%>
					</div>
				<%} colCount++;%>
			</c:forEach>
		</c:if>
		</div>
		
	</div>
	
</div>
<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>