<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<div id="pg-posts" class="">
	<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
	<div class="container pg-admin">
		<div class="row">
			<div class="col-sm-3 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 content content-fluid nopadding">
				<div id="content-menu" class="menu-list">
						<h1>Posts</h1>
				</div>
				<div id="pg-post-category">
					<c:if test="${fn: length(adverts) > 0}">
						<table class="w-100">
							<thead>
								<tr>
									<th>Title</th>
									<th>User</th>
									<th>Status</th>
									<th>Submitted</th>
									<th>Approve</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
								<%boolean odd = true;%>
								<c:forEach items="${adverts}" var="advert">						
									<%if (odd) {%>
									<tr class="odd-row">
									<%} else {%>									
									<tr class="even-row">
									<%}%>
										<td class="text-capitalize pl-1 w-25"><a
											href="/adpostm/advert/detail?id=${advert.getAdvertId()}"
											class="ad-dtl-link">${advert.advertDetail.getTitle()}</a></td>
										<td class="w-25">${advert.appUser.userDetail.getFirstName()} 
										</td>
										<td class="w-25">${advert.getAdvertStatus()}</td>
										<td class="w-25">
											<fmt:formatDate value="${advert.getSubmittedDate()}" 
												pattern="dd MMM yyyy" />
										</td>
										<td><input type="checkbox" name="advertStatus" class="adStatusCheck"
											 <c:if test="${advert.getAdvertStatus() == 'APPROVED'}">
											 	checked="checked"
											 </c:if>
											 onchange="UpdateAdStatus(${advert.getAdvertId()}, this)"
											 class="round"/>
										</td>
										<td>
											<a href="/adpostm/advert/edit?id=${advert.getAdvertId()}">Edit</a>
										</td>
									</tr>
									<%odd = !odd;%>
								</c:forEach>
							</tbody>
						</table>
						<br>
					</c:if>
				</div>

			</div>
		</div>
		<br />
	</div>
	<jsp:include page="/WEB-INF/views/jsp/postDetail.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>