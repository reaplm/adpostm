<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.text.SimpleDateFormat, java.util.Date" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="sidebar" class="collapse show">
	
	<div id="" class="user-info">
		
		<div class="user-info-img" >
			<img src="<%=session.getAttribute("profileImage")%>" />
				
		</div>
		<div class="user-info-txt">
			<%
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyy"); 
				Date registrationDate = (Date)session.getAttribute("registrationDate");
				String dateText = format.format(registrationDate);
			%>
			<p class="text-center card-title">
				<a href="/adpostm/admin/profile"><%=session.getAttribute("firstName") %></a>
			</p>
			<p class="text-center">Member Since <%=dateText%></p>
		</div>
	</div>
	<div id="sidebar-accordion" role="tablist" aria-multiselectable="true" class="text-capitalize">
		<c:forEach items="${sideMenu}" var="menu" varStatus="loop">
			<div class="card">
				<div class="card-header" role="tab">
					<h5 class="mb-0">
						<a data-toggle="collapse" data-parent="#sidebar-accordion"  
							href="#${menu.getLabel()}" 
							<c:if test="${loop.index==0}">								
								aria-expanded="true"  
							</c:if>
							<c:if test="${loop.index > 0}">
								aria-expanded="false"  
								class="collapsed"
							</c:if>
							aria-controls="${menu.getMenuName()}">
							${menu.getMenuName()}
						</a>
					</h5>
				</div>
				<c:if test="${fn: length(menu.getSubMenu()) > 0}">
					<div id="${menu.getLabel()}"
						<c:if test="${loop.index == 0}">
							class="collapse show" 
						</c:if>
							<c:if test="${loop.index > 0}">
								class="collapse"
							</c:if>
						role="tabpanel" aria-labelledby="${menu.getLabel()}">
						<div class="card-block">
							<ul class="list-unstyled">
								<c:forEach items="${menu.subMenu}" var="subMenu">
									<li ><a href="/adpostm/admin/${subMenu.getLabel()}" class="acc-nav-link">
										${subMenu.getMenuName()}</a></li>
									<li class="dropdown-divider"></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div id="" class="sidebar-footer card-header">

	</div>
</div>
