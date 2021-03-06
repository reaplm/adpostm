<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/resources/js/d3Script.js"
	type="text/javascript"></script>

<div id="pg-dashboard">
	<div class="container content pg-admin mt-5 mb-3 shadow">
		<div class="row">
			<div class="col-sm-2 col-lg-3 nopadding">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-10 col-lg-9 content-fluid nopadding">
				<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
				<div class="container">
					<div class="row mb-2 ml-1 mr-1 mt-2">
						<div class="col-sm-8 graph pr-1">
							<div class="card">
								<div class="card-header">
									<h5 class="mt-2">Adverts By Location</h5>
								</div>
								<div class="card-body" style="height: 200px"></div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="row">
								<div id="adStatusGraph" class="col-sm-12 graph">
									<div class="card">
										<div class="card-header">
											<h5 class="mt-2">Advert Status</h5>
										</div>
										<div class="card-body"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div id="adsPerCategory" class="col-sm-12 graph">
									<div class="card">
										<div class="card-header">
											<h5 class="mt-2">Advert Categories</h5>
										</div>
										<div class="card-body"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mb-2 mt-2 mr-1 ml-1">
						<div class="col-sm-12 graph">
							<div class="card">
								<div class="card-header">
									<h5 class="mt-2">Recent Adverts</h5>
								</div>
								<div class="card-body">
									<div id="ads-table">
										<c:if test="${fn: length(adverts) > 0}">
											<table class="w-100">
												<thead>
												</thead>
												<tbody>
													<%
														boolean odd = true;
													%>
													<c:forEach items="${adverts}" var="advert">
														<%
															if (odd) {
														%>
														<tr class="odd-row">
															<%
																} else {
															%>
														
														<tr class="even-row">
															<%
																}
															%>
															<td class="text-capitalize pl-1 w-25"><a
																href="/adpostm/advert/detail?id=${advert.getAdvertId()}"
																class="ad-dtl-link">${advert.advertDetail.getTitle()}</a></td>

															<td class="w-25">${advert.getAdvertStatus()}</td>
															<td class="w-25"><fmt:formatDate
																	value="${advert.getSubmittedDate()}"
																	pattern="dd MMM yyyy" /></td>
															<td><input type="checkbox" name="advertStatus"
																class="adStatusCheck"
																<c:if test="${advert.getAdvertStatus() == 'APPROVED'}">
														 	checked="checked"
														 </c:if>
																onchange="UpdateAdStatus(${advert.getAdvertId()}, this)"
																class="round" /></td>
															<td><a
																href="/adpostm/advert/edit?id=${advert.getAdvertId()}">Edit</a>
															</td>
														</tr>


														<%
															odd = !odd;
														%>

													</c:forEach>
												</tbody>
											</table>
											<br>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row mb-2 mr-1 ml-1">
						<div class="col-sm-6 graph">
							<div class="card">
								<div class="card-header">
									<h5 class="mt-1">New Users</h5>
								</div>
								<div class="card-body">
									<c:if test="${fn: length(users) > 0}">
										<c:forEach var="user" items="${users}">
											<div class="ml-1">
												<img class="float-left pr-2 rounded-img"
													src="${user.userDetail.getImageCdn()}" width="60px"
													height="60px" />
												<div class="">

													<a href="/adpostm/advert/detail?id=${user.getAppUserId()}"
														class="ad-dtl-link"> ${user.userDetail.getFirstName()}
														${user.userDetail.getLastName()} </a>
													<p class="mb-0">${user.getEmail()}</p>
													<p>
														Registered
														<fmt:formatDate value="${user.getRegistrationDate()}"
															pattern="dd MMM yyyy" />
													</p>
												</div>
											</div>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-sm-6 graph">
							<div class="card">
								<div class="card-header">
									<h5 class="mt-2">Liked Ads</h5>
								</div>
								<div class="card-body">
									<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<jsp:include page="/WEB-INF/views/jspinc/footer.jsp" />
	</div>