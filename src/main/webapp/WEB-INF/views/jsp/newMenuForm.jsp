<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="form-menu-add">
	<div class="modal fade" id="menu-add-modal" tabindex="-1"
		role="dialog" aria-labelledby="addMenuModal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="ml-5">Create Menu</h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<form:form action="/adpostm/menus/add" method="post" class="form-horizontal w-75" 
									id="add-menu-form">
									<!-- 
								<div class="form-group row">
									<div class="col-sm-4 text-right">
										<label for="name">Parent Menu</label>
									</div>
									<div class="col-sm-8">
										<input type="text" name="parentId"
										id="parentId" class="form-control"/>
									</div>
								</div>
								-->
								<div class="form-group row has-danger">
									<div class="col-sm-4 text-right">
										<label for="menuName">Menu Name</label>
									</div>
									<div class="col-sm-8">
										<input type="text" name="menuName"
										id="menuName" class="form-control"/>
										<div class="error-div">
											<label id="menuName-error" class="form-control-danger"
											 for="menuName"></label>
										</div>
									</div>
									 
								</div>
								<div class="form-group row">
									<div class="col-sm-4 text-right">
										<label for="menuDesc" >Description</label>
									</div>
									<div class="col-sm-8">
										<input type="text"
										name="menuDesc" id="menuDesc" class="form-control" />
									</div>
									 
								</div>
								<div class="form-group row has-danger">
									<div class="col-sm-4 text-right">
										<label for="icon">Icon</label>
									</div>
									<div class="col-sm-8">
										<input type="text" name="icon"
										id="icon" class="form-control"/>
										<div class="error-div">
											<label id="icon-error" class="form-control-danger"
											 for="icon"></label>
										</div>
									</div>
									 
								</div>
							</form:form>							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onClick="ValidateAddMenu()">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>