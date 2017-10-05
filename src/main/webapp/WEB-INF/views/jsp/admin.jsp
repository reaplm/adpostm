<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="pg-menu-admin">
		<c:if test="${fn: length(adminMenu) > 0}">
		<c:forEach items="${adminMenu}" var="admin">
			<div class="clearfix mt-2">
				<img src="<%=request.getContextPath()%>/resources/
						images/menu/${admin.getIcon()}" class="float-left" />
				<h5>
					<a href="/adpostm/menus/detail?id=${admin.getMenuId()}" 
					class="dtl-link text-capitalize">${admin.getMenuName()}</a>
				</h5>
			</div>
			<c:if test="${fn: length(admin.getSubMenu()) > 0}">
				<table class="w-100">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Url</th>
							<th>Label</th>
						</tr>
					</thead>
					<tbody>
							<%boolean odd = true; %>
							<c:forEach items="${admin.subMenu}" var="adminSub">
								<%if(odd){ %>
								<tr class="odd-row">
								<%}else{ %>
								<tr class="even-row">
								<%} %>
									<td class="text-capitalize pl-1 w-25"><a href="/adpostm/menus/sub/detail?id=${adminSub.getMenuId()}" 
										class="dtl-link">${adminSub.getMenuName()}</a></td>
									<td class="w-25">${adminSub.getMenuDesc()}</td>
									<td class="w-25">${adminSub.getUrl()}</td>
									<td class="w-25">${adminSub.getLabel()}</td>
								</tr>
								<%odd=!odd; %>
							</c:forEach>
					</tbody>
				</table>
				<br>
			</c:if>
		</c:forEach>
	</c:if>
</div>