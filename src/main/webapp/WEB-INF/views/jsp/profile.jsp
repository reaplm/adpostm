<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.text.SimpleDateFormat, java.util.Date"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<div id="pg-menus" class="">
		

	<div class="container pg-admin mt-5 mb-3 shadow ">
		<div class="row">
			<div class="col-sm-3 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 content content-fluid nopadding">
				<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
				<%
					SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyy HH:mm aaa");
					Date registrationDate = (Date) session.getAttribute("registrationDate");
					Date lastLoginDate = (Date) session.getAttribute("lastLogin");
					String regText = format.format(registrationDate);
					String loginText = format.format(lastLoginDate);
				%>
				<div class="row">
					<div class="col-sm-3 pr-0">
						<div class="card-header ml-1 mt-1  nopadding">
							<img class="card-image-top"
								src="<%=session.getAttribute("profileImage")%>" width="100%"
								id="upload-profile-img"
								onClick="openUploadCareDialog('<%=session.getAttribute("profileImage")%>');"
								role="button" />
							<div class="card-block">
								<h4 class="card-title"><%=session.getAttribute("firstName")%></h4>
							</div>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Member Since <%=regText%></li>
								<li class="list-group-item">You were last online on <%=loginText%></li>
							</ul>
						</div>

					</div>
					<div class="col-sm-9">
						<div class="card mr-2 mt-1">
							<div class="card-header">
								<h4 class="card-title">Account</h4>
								<hr>
								<div class="d-flex  justify-content-between">
									<p class="d-inline-block">Account Type</p>
									<p class="d-inline-block">Free</p>
								</div>
								<div class="d-flex  justify-content-between">
									<p class="d-inline-block">Username</p>
									<p class="d-inline-block"><%=session.getAttribute("username")%></p>
								</div>
								<div class="d-flex  justify-content-between">
									<p class="d-inline-block">Password</p>
									<p class="d-inline-block">*********</p>
									<a href="#">Edit</a>
								</div>

								<div class="d-flex  justify-content-between">
									<p class="d-inline-block">Registration Date</p>
									<p class="d-inline-block"><%=regText%></p>
								</div>
								<div class="d-flex  justify-content-between">
									<p class="d-inline-block">Last Login Date</p>
									<p class="d-inline-block"><%=loginText%></p>
								</div>
							</div>
						</div>
						<div class="card mr-2 mt-1">
							<div class="card-header">
								<h4 class="card-title">Personal Information</h4>
								<hr>
								<div class="d-flex  justify-content-between">
									<p class="d-inline-block">Name</p>
									<p class="d-inline-block">${user.userDetail.getFirstName()}
										${user.userDetail.getLastName()}</p>
									<a href="#">Edit</a>
								</div>
							</div>

						</div>
						<div class="card mr-2 mt-1">
							<div class="card-header">
								<h4 class="card-title">Contact Information</h4>
								<hr>
								<div class="d-flex  justify-content-between">
									<p class="w-25">Address</p>
									<p class="">${user.userDetail.getAddress().getPostAddress1()}<br />
										${user.userDetail.getAddress().getPostAddress2()}
										${user.userDetail.getAddress().getSurbub()}
										${user.userDetail.getAddress().getPostCode()}<br />
										${user.userDetail.getAddress().getState()}
									</p>
									<a
										href="/adpostm/user?username=<%=session.getAttribute("username")%>"
										id="edit-address" class="ml-auto">Edit</a>
								</div>
								<div class="d-flex  justify-content-between">
									<p class="w-25">Mobile No</p>
									<p class="">${user.userDetail.getMobileNo()}</p>
									<p class="ml-auto"></p>
									<a href="/adpostm/user/id?id=${user.getAppUserId()}" 
										class="ml-auto" id="edit-contact">Edit</a>
								</div>
								<div class="d-flex  justify-content-between">
									<p class="w-25">Location</p>
									<p class="pl-5"></p>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<br />
			
		</div>
		<jsp:include page="/WEB-INF/views/jsp/address.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/jsp/contact.jsp"></jsp:include>
		
	</div>
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
</div>