<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="pg-menu-category">
	<c:if test="${fn: length(catMenu) > 0}">
		<table class="w-100">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Url</th>
					<th>Icon</th>
				</tr>
			</thead>
			<tbody>
			<%boolean odd = true; %>
			<c:forEach items="${catMenu}" var="cat">
				<%if(odd){ %>
				<tr class="odd-row">
				<%}else{ %>
				<tr class="even-row">
				<%} %>
					<td class="text-capitalize pl-1"><a href="/adpostm/menus/detail?id=${cat.getMenuId()}" class="dtl-link">${cat.getMenuName()}</a></td>
					<td>${cat.getMenuDesc()}</td>
					<td>${cat.getUrl()}</td>
					<td>${cat.getIcon()}</td>
				</tr>
				<%odd=!odd; %>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>