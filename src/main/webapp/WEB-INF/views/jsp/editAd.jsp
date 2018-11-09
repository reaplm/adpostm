<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">`
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
	
<div id="pg-edit-ad">
	<jsp:include page="/WEB-INF/views/jspinc/adminMenu.jsp"></jsp:include>
	<div class="container pg-admin">
		<div class="row">
			<div class="col-sm-3 nopadding pr-1">
				<jsp:include page="/WEB-INF/views/jspinc/sidebar.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 content content-fluid nopadding">
				<form:form id = "edit-advert-form" method="post" action="/adpostm/advert/edit/submit" 
					modelAttribute="advertInfo" class="card-header">
					<div class="form-row has-danger">
						<p>CATEGORY: </p> 
						<button type="button" onclick="CloseEditAd()" title="close" class="close float-right">x</button>
						<form:select id="menuId" class="text rounded-box menu-select" 
						name="menuId" path="menuId">
							<option value="-1" disabled>----------Category--------</option>
							<c:if test="${fn: length(sessionScope.homeMenu) > 0}">
								<c:forEach var="menu" items="${sessionScope.homeMenu}">
									<c:if test="${menu.menuId eq advertInfo.menuId}">
										<option value="${menu.menuId}" selected>${menu.menuName}</option>
										<c:set var="subMenuList" value="${menu.getSubMenu()}" />
									</c:if>
									<c:if test="${menu.menuId ne advertInfo.menuId}">
										<option value="${menu.menuId}">${menu.menuName}</option>
									</c:if>
								</c:forEach>
							</c:if>
						</form:select>	
						
						<form:select id="subMenuId" class="text rounded-box submenu-select" 
							name="subMenuId" path="subMenuId" placeholder="Sub-Menu">
							<option value="-1" disabled selected>Sub-Category</option>
							<c:forEach var="subMenu" items="${subMenuList}">
							
								<c:if test="${subMenu.menuId eq advertInfo.subMenuId}">
										<option value="${subMenu.menuId}" selected>${subMenu.menuName}</option>
									</c:if>
									<c:if test="${subMenu.menuId ne advertInfo.subMenuId}">
										<option value="${subMenu.menuId}">${subMenu.menuName}</option>
									</c:if>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-row has-danger">
					
						<p>LOCATION: </p>
						<form:input type="text" placeholder="location" class="text form-control" 
									id="location" name="location" path="location" maxlength="40"
									/>
								
					</div>
					<div class="form-row has-danger">
						<p>AD DETAILS: </p>
						<form:input type="text" class="text form-control" placeholder="subject"
										id="subject" name="subject" path="subject" maxlength="40"/>
										 <br />
						<form:textarea rows="10" cols="100"	id="body" name="body"
							path="body" class="text form-control" ></form:textarea>
								
					</div>
					<div class="form-row has-danger">
						<p>CONTACT: </p>
						<form:input type="text" placeholder="phone" class="text form-control" 
										id="contactNo" name="contactNo" path="contactNo" maxlength="20"
										/>
						<form:input type="text" placeholder="email" class="text form-control" 
										name="contactEmail" id="contactEmail" path="contactEmail"
										/>
					</div>
					<br />
					<div class="clearfix">    
						<c:set var="pictureList" value="${advertInfo.getImageCdnUrl()}" />
						<c:if test="${fn: length(pictureList) > 0}">
							<c:forEach var="adCdnUrl" items="${advertInfo.getImageCdnUrl()}">
								<div class="card float-left img-thumb">
									<div class="card-header">
										<button type="button" class="close">x</button>
									</div>
									<div class="img-thumb-body">
									<img class="card-img-top" src="${adCdnUrl}" 
										alt="Card image cap" width="100%"/>
									</div>
									<div class="card-footer">
								      <small class="text-muted">small text</small>
								    </div>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<br />
					<form:input type='hidden' id='advertId' name='advertId' path='advertId' />
					<form:input type='hidden' id='groupUuid' name='groupUuid' path='groupUuid' />
					


					<div class="form-row form-footer">
						<input name="uploadCareUrl" type="hidden" id="editAdWidget"
								role="uploadcare-uploader" data-images-only="true" 
								data-multiple="true" data-multiple-max="4" 
								class="float-left"/> 
						<button class="img-button button" type="button"
							onclick="ValidateEditAdvert()"><img src="<%=request.getContextPath()%>/resources/
											images/right_arrow.png" width="40px" /></button>
						
					</div>
					<br />
				</form:form>	
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
</div>