<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="pg-menu-admin">
		<c:if test="${fn: length(sideMenu) > 0}">
		<c:forEach items="${sideMenu}" var="admin">
			<div class="clearfix mt-2">
				<img src="<%=request.getContextPath()%>/resources/
						images/menu/${admin.getIcon()}" class="mr-3 float-left" />
				<h5 class="pt-2">
					<a href="/adpostm/menus/detail?id=${admin.getMenuId()}" 
					class="dtl-link text-capitalize">${admin.getMenuName()}</a>
				</h5>
				<input type="checkbox" class="float-right"
					<c:if test="${admin.getMenuStatus() == 1}">
						checked="checked"
						<c:set var="disabled" value="false" />
					</c:if>
					<c:if test="${admin.getMenuStatus() == 0}">
						checked="checked"
						<c:set var="disabled" value="true" />
					</c:if>
					onchange="UpdateMenuStatus(${admin.getMenuId()}, this)"
				/>
			</div>
			<c:if test="${fn: length(admin.getSubMenu()) > 0}">
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
							<c:forEach items="${admin.subMenu}" var="adminSub">
								<%if(odd){ %>
								<tr class="odd-row">
								<%}else{ %>
								<tr class="even-row">
								<%} %>
									<td class="text-capitalize  td-md"><a href="/adpostm/menus/sub/detail?id=${adminSub.getMenuId()}" 
										class="dtl-link">${adminSub.getMenuName()}</a></td>
									<td class="td-md">${adminSub.getMenuDesc()}</td>
									<td class="td-md">${adminSub.getUrl()}</td>
									<td class="td-md">${adminSub.getLabel()}</td>
									<td class="td-sm">
									<input type="checkbox" class="subMenuCheck"
										<c:if test="${adminSub.getAdminMenu() == 1}">
											checked="checked"
										</c:if>
										onchange="UpdateMenuAdmin(${adminSub.getMenuId()}, this)"
									/>
								</td>
								<td class="td-sm">
									<input type="checkbox" class="subMenuCheck subMenuCheck-${admin.getMenuId()}"
										<c:if test="${adminSub.getMenuStatus() == 1}">
											checked="checked"
										</c:if>
										<c:if test="${disabled eq 'true'}">
											disabled
										</c:if>
										onchange="UpdateMenuStatus(${adminSub.getMenuId()}, this)"
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