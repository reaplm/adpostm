<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.List, java.util.Iterator,
    java.text.SimpleDateFormat, java.util.Date, com.adpostm.domain.model.Menu,
    java.util.Date"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/jspinc/header.jsp" />
	<jsp:include page="/WEB-INF/views/jspinc/searchbar.jsp" />
	<div id="pg-adverts">
		<div class="container col-layout col-fill-auto">
	    	<c:if test="${fn: length(advertList) > 0}">
	    		<c:set scope="request" var="menuId" value="0" />
				<c:forEach var="advert" items="${advertList}">
					
					<c:if test="${advert.menu.getMenuId() ne menuId}">
						<h5 class="text-uppercase text-center  font-weight-bold dark-bg">
							<c:out value = "${advert.menu.getMenuName()}"/>
						</h5>
						<c:set scope="request" var="menuId" value="${advert.menu.getMenuId()}" />
					</c:if>
					
						<p class="text-uppercase text-left  font-weight-bold">
							<a href="/adpostm/advert/detail?id=${advert.getAdvertId()}" 
							class="ad-dtl-link title">
							${advert.advertDetail.getTitle()}</a>
						</p>
						<p class="text-capitalize">${advert.advertDetail.getLocation()}</p>
						<br />
				 	<p class="text-capitalize">${advert.advertDetail.getBody()}</p>
				
					 <hr />
				</c:forEach>
						
			</c:if>											
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/jsp/postDetail.jsp"></jsp:include>
	