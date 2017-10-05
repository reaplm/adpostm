<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="pg-menu-category">
	<c:if test="${fn: length(catMenu) > 0}">
		<c:forEach items="${catMenu}" var="cat">
			<div class="clearfix align-text-bottom">
				<img src="<%=request.getContextPath()%>/resources/
						images/menu/${cat.getIcon()}" class="float-left" />
				<h5 class="mt-2">
					<a href="/adpostm/menus/detail?id=${cat.getMenuId()}" 
						class="dtl-link text-capitalize">${cat.getMenuName()}</a>
				</h5>
			</div>
			<c:if test="${fn: length(cat.getSubMenu()) > 0}">
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
						<c:forEach items="${cat.subMenu}" var="catSub">
							<%if(odd){ %>
							<tr class="odd-row">
							<%}else{ %>
							<tr class="even-row">
							<%} %>
								<td class="text-capitalize pl-1 w-25"><a href="/adpostm/menus/sub/detail?id=${catSub.getMenuId()}" 
									class="dtl-link">${catSub.getMenuName()}</a></td>
								<td class="w-25">${catSub.getMenuDesc()}</td>
								<td class="w-25">${catSub.getUrl()}</td>
								<td class="w-25">${catSub.getLabel()}</td>
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