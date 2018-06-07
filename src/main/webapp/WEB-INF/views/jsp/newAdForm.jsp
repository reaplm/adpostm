<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<form id = "add-advert-form" method="post" action="/adpostm/advert/add">
			<div class="form-row">
				<p>CATEGORY: </p>
				<select id="menuId" class="text" 
				name="menuId" path="menuId" >
						<option value="-1" disabled selected>Category</option>
					<c:forEach var="menu" items="${menuList}">
						<option value="${menu.getMenuId()}">${menu.getMenuName()}</option>
					</c:forEach>
				</select>	
				
				<select id="subMenuId" class="text" 
					name="subMenuId" path="subMenuId" placeholder="Sub-Menu">
					<option value="-1" disabled selected>Sub-Category</option>
				</select>
			</div>
			<div class="form-row">
			
				<p>LOCATION: </p>
				<input type="text" placeholder="location" class="text form-control" 
							id="adLocation" name="adLocation" path="adLocation"/>
						
			</div>
			<div class="form-row">
				<p>AD DETAILS: </p>
				<input type="text" class="text" placeholder="subject"
								id="adSubject" name="adSubject" path="adSubject"/> <br />
				<textarea rows="10" cols="100"	id="adBody" name="adBody"
				path="adBody" class="text form-control" ></textarea>
						
			</div>
			<div class="form-row">
				<p>CONTACT: </p>
				<input type="text" placeholder="phone" class="text " 
								id="contactNo" name="contactNo" path="contactNo"/>
				<input type="text" placeholder="email" class="text form-control" 
								name="contactEmail" id="contactEmail" path="contactEmail"/>
			</div>
			<br />
			<div class="form-row">
				<input name="uploadCareUrl" type="hidden" id="uploadcareWidget"
						role="uploadcare-uploader" data-images-only="true" 
						data-multiple="true" data-multiple-max="4" path="uploadCareUrl"/> 
						<button class="img-button button" type="button"
					onclick="SubmitAdvert(); return false;"></button>
				
			</div>
			<br />
		</form>	
	