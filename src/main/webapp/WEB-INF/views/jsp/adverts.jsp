<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List, java.util.Iterator,
    java.text.SimpleDateFormat, java.util.Date, com.adpostm.domain.model.Menu,
    java.util.Date"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/jspinc/header.jsp" />
<jsp:include page="/WEB-INF/views/jspinc/searchbar.jsp" />
<div id="pg-adverts">
	<div class="container">
		<div class="col-layout">
			<c:if test="${fn: length(sessionScope.homeMenu) > 0}">
				<!-- Get main menu items -->
				<c:forEach var="menu" items="${sessionScope.homeMenu}">
					<h5 class="text-uppercase text-center  font-weight-bold dark-bg">
						<c:out value="${menu.getMenuName()}" />
					</h5>
					<!-- Get sub-menu items -->
					<c:set var="subMenus" value="${menu.getSubMenu()}" />
					
					<c:if test="${fn: length(subMenus) > 0}">
						
						<!-- For each sub-menu get all adverts -->
						<c:forEach var="subMenu" items="${subMenus}">
							<!-- For each sub-menu get all adverts -->
							<c:set var="adverts" value="${subMenu.getAdverts()}" />
							<c:forEach var="advert" items="${adverts}">
								<!-- For each advert prints info-->
								<c:if test="${fn: length(adverts) > 0}">
									<c:forEach var="advert" items="${adverts}">
										<p class="text-uppercase text-left  font-weight-bold">
											<a href="/adpostm/advert/detail?id=${advert.getAdvertId()}"
												class="ad-dtl-link title"> ${advert.advertDetail.getTitle()}</a>
										</p>
										<p class="text-capitalize">${advert.advertDetail.getLocation()}</p>
										<br />
										<p class="text-capitalize">${advert.advertDetail.getBody()}</p>
				
										<hr />
									</c:forEach>
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/jsp/postDetail.jsp"></jsp:include>
