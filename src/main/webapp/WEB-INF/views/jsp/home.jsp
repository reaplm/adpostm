<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>

<div id="pg-home">
	
	<div class="container">
		<div class="row mb-3" >
			<jsp:include page="/WEB-INF/views/jspinc/header.jsp" />
		</div>
		<div class="row mb-5" >
			<jsp:include page="/WEB-INF/views/jspinc/searchbar.jsp"></jsp:include>
		</div>
		<div class="row">
			<%int colCount = 0; %>
			<c:if test="${fn: length(menus) > 0}">
				<c:forEach items="${menus}" var="menu">
					<div class="col-6 col-sm-3 category mb-2">
							<div class="clearfix">
								<img src="<%=request.getContextPath()%>/resources/
									images/menu/${menu.getIcon()}" class="float-left"/>
								<h5 class="pt-2">
									<a href="/adpostm/advert/search?search=&s-category=${menu.getMenuId()}">
										${menu.getMenuName()}
									</a>
								</h5>
							</div>
						
						<c:forEach items="${menu.subMenu}" var="subMenu">
							<p class="pl-2 mb-1"><a href="#">${subMenu.getMenuName()}</a></p>
						</c:forEach>
					</div>
					
				</c:forEach>
			</c:if>
		</div>
	</div>
	
</div>
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>

