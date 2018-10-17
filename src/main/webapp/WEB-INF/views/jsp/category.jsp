<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="pg-menu-category">
	<c:if test="${fn: length(homeMenu) > 0}">
		<c:forEach items="${homeMenu}" var="cat">
			<div class="clearfix align-text-bottom clearfix mt-2">
				<img src="<%=request.getContextPath()%>/resources/
						images/menu/${cat.getIcon()}" class="mr-3 float-left" />
				<h5 class="pt-2">
					<a href="/adpostm/menus/detail?id=${cat.getMenuId()}" 
						class="dtl-link text-capitalize">${cat.getMenuName()}</a>
				</h5>
				<input type="checkbox" class="float-right"
					<c:if test="${cat.getMenuStatus() == 1}">
						checked="checked"
					</c:if>
					onchange="UpdateMenuStatus(${cat.getMenuId()}, this)"
				/>
			</div>
			<c:if test="${fn: length(cat.getSubMenu()) > 0}">
			<table class="w-100">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Url</th>
						<th>Label</th>
						<th>Admin Only</th>
						<th>Active</th>
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
								<td class="text-capitalize pl-1 td-md"><a href="/adpostm/menus/detail?id=${catSub.getMenuId()}" 
									class="dtl-link">${catSub.getMenuName()}</a></td>
								<td class="td-md">${catSub.getMenuDesc()}</td>
								<td class="td-md">${catSub.getUrl()}</td>
								<td class="td-md">${catSub.getLabel()}</td>
								<td class="td-sm">
									<input type="checkbox" class="subMenuCheck"
										<c:if test="${catSub.getAdminMenu() == 1}">
											checked="checked"
										</c:if>
										onchange="UpdateMenuAdmin(${catSub.getMenuId()}, this)"
									/>
								</td>
								<td class="td-sm">
									<input type="checkbox" class="subMenuCheck"
										<c:if test="${catSub.getMenuStatus() == 1}">
											checked="checked"
										</c:if>
										onchange="UpdateMenuStatus(${catSub.getMenuId()}, this)"
									/>
				
								</td>
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