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
		<div class="container">
	    	<c:if test="${fn: length(advertList) > 0}">
	    		<hr />
				<c:forEach var="advert" items="${advertList}">
					<div class="row mb-3 advert-list">	
							<div class="col-sm-3">
								<img src="<%=request.getContextPath()%>/resources/
									images/no-image-icon.png"/>
							</div>
							<div class="col-sm-9" >
								<h5><a href="/AdPost/adverts?id=${advert.getAdvertId()}" 
									class="advert-details-link title">
									${advert.advertDetail.getTitle()}</a></h5>
									<p>${advert.advertDetail.getLocation()}</p>
							 	<p>${advert.advertDetail.getBody()}</p>
							 </div>
					</div>
					 <hr />
				</c:forEach>
						
			</c:if>											
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>