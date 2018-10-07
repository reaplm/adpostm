<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<div id="pg-menus" class="">
	<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
	<div class="container pg-admin">
		<div class="row">
			<div class="col-sm-3 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 content content-fluid nopadding">
				<div id="content-menu" class="menu-list">
						<h1>Users</h1>
				</div>
				<div id="pg-post-category">
					<c:if test="${fn: length(users) > 0}">
						<table class="w-100">
							<thead>
								<tr>
									<th>Name</th>
									<th>Email</th>
									<th>Roles</th>
									<th>Mobile No</th>
									<th>Last Login</th>
									<th>Registration</th>
								</tr>
							</thead>
							<tbody>
								<%boolean odd = true;%>
								<c:forEach items="${users}" var="user">						
									<%if (odd) {%>
									<tr class="odd-row">
									<%} else {%>									
									<tr class="even-row">
									<%}%>
										<td class="text-capitalize pl-1"><a
											href="/adpostm/advert/detail?id=${user.getAppUserId()}"
											class="ad-dtl-link">
											${user.userDetail.getFirstName()} ${user.userDetail.getFirstName()}
											</a>
										</td>
										<td class="">${user.getEmail()} 
										</td>
										<td class="">
											<c:if test="${fn: length(user.getRoles()) > 0}">
												<c:forEach items="${user.getRoles()}" var="role">
													<c:out value="${role.getRoleName()}" />
												</c:forEach>
											</c:if>
											</td>
										<td class="">${user.userDetail.getMobileNo()}</td>
										<td class="">
											<fmt:formatDate value="${user.getRegistrationDate()}" 
												pattern="dd MMM yyyy" />
										</td>
										<td class="">
											<fmt:formatDate value="${user.getLastLoginDate()}" 
												pattern="dd MMM yyyy" />
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
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/jsp/editMenuForm.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/jsp/newMenuForm.jsp"></jsp:include>
</div>