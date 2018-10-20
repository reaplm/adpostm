<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.adpostm.domain.model.Menu"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="form-menu-edit">
	<div class="modal fade" id="menu-edit-modal" tabindex="-1"
		role="dialog" aria-labelledby="editMenuModal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<img src="" />
					<h1>
						<span id="spanName"></span>
					</h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
					
				</div>
				<div class="modal-body">
					<div class="container">
						
						<div class="row border-btm">
							<form:form action="/adpostm/menus/update" method="post" class="form-horizontal" 
									id="edit-menu-form" modelAttribute="menu">
								<div class="form-group row">
									<div class="col-sm-6">
										<label for="menuName">Name
											<form:input type="text" name="menuName"
												id="menuName" class="form-control" path="menuName" />
										</label>
									</div>
									<div class="col-sm-6">
										<label for="menuDesc" >Description
											<form:input type="text" name="menuDesc" id="menuDesc" 
												class="form-control" path="menuDesc" />
										</label>
									</div>
									 
								</div>
								
								<div class="form-group row">
									
									<div class="col-sm-6">
										<label for="url">Url
											<form:input type="text" name="url"
												id="url" class="form-control" path="url" />
										</label>
									</div>
									 <div class="col-sm-6">
										<label for="icon" >Icon
											<form:input type="text" name="icon"
												id="icon" class="form-control" path="icon"/>
										</label>
									</div>
								</div>
								<div class="form-group">
									<form:input type="hidden" id="menuId" name="menuId" path="menuId" />
									<form:input type="hidden" id="menuType" name="menuType" path="menuType" />
									<form:input type="hidden" id="adminMenu" name="adminMenu" path="adminMenu" />
									<form:input type="hidden" id="menuStatus" name="menuStatus" path="menuStatus" />
								</div>
							</form:form>							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary">delete</button>
					<button type="button" class="btn btn-primary" onclick="ValidateUpdateMenu()">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>