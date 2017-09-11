<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="pg-menu-admin">
	<div class="container">
		<div class="row">
				<c:if test="${fn: length(adminMenu) > 0}">
					<table>
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Url</th>
								<th>Icon</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${adminMenu}" var="admin">
							<tr>
								
								<td><a href="#">${admin.getMenuName()}</a></td>
								<td>${admin.getMenuDesc()}</td>
								<td>${admin.getUrl()}</td>
								<td>${admin.getIcon()}</td>
								
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
</div>