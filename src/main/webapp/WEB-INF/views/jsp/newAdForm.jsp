<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<form:form id = "add-advert-form" method="post" action="/adpostm/advert/add" 
			modelAttribute="advertInfo" class="card-header">
			<div class="form-row has-danger">
				<p>CATEGORY: </p>
				<form:select id="menuId" class="text rounded-box" 
				name="menuId" path="menuId">
						<option value="-1" disabled selected>Category</option>
					<c:forEach var="menu" items="${menuList}">
						<option value="${menu.getMenuId()}">${menu.getMenuName()}</option>
					</c:forEach>
				</form:select>	
				
				<form:select id="subMenuId" class="text rounded-box" 
					name="subMenuId" path="subMenuId" placeholder="Sub-Menu">
					<option value="-1" disabled selected>Sub-Category</option>
				</form:select>
			</div>
			<div class="form-row has-danger">
			
				<p>LOCATION: </p>
				<form:input type="text" placeholder="location" class="text form-control" 
							id="location" name="location" path="location"/>
						
			</div>
			<div class="form-row has-danger">
				<p>AD DETAILS: </p>
				<form:input type="text" class="text form-control" placeholder="subject"
								id="subject" name="subject" path="subject"/> <br />
				<form:textarea rows="10" cols="100"	id="body" name="body"
					path="body" class="text form-control" ></form:textarea>
						
			</div>
			<div class="form-row has-danger">
				<p>CONTACT: </p>
				<form:input type="text" placeholder="phone" class="text form-control" 
								id="contactNo" name="contactNo" path="contactNo"/>
				<form:input type="text" placeholder="email" class="text form-control" 
								name="contactEmail" id="contactEmail" path="contactEmail"/>
			</div>
			<br />
			<div class="form-row form-footer">
				<input name="uploadCareUrl" type="hidden" id="uploadcareWidget"
						role="uploadcare-uploader" data-images-only="true" 
						data-multiple="true" data-multiple-max="4" 
						class="float-left"/> 
				<button class="img-button button" type="button"
					onclick="ValidateSubmitAdvert()"><img src="<%=request.getContextPath()%>/resources/
									images/right_arrow.png" width="40px" /></button>
				
			</div>
			<br />
		</form:form>	
	