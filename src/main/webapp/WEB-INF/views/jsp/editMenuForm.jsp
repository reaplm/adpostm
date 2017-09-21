<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
						<div class="row">
							<form:form action="/adpostm/menus/update" method="post" class="form-horizontal" 
									id="edit-menu-form" modelAttribute="menu">
								<div class="form-group row">
									<div class="col-sm-4 text-right">
										<label for="name" path="menuName">Name</label>
									</div>
									<div class="col-sm-8">
										<input type="text" name="menuName"
										id="menuName" class="form-control" path="menuName"/>
									</div>
									 
								</div>
								<div class="form-group row">
									<div class="col-sm-4 text-right">
										<label for="desc" path="menuDesc" >Description</label>
									</div>
									<div class="col-sm-8">
										<input type="text"
										name="menuDesc" id="menuDesc" class="form-control" path="menuDesc" />
									</div>
									 
								</div>
								<div class="form-group row">
									<div class="col-sm-4 text-right">
										<label for="url" path="url">Url</label>
									</div>
									<div class="col-sm-8">
										<input type="text" name="url"
										id="url" class="form-control" path="url" />
									</div>
									 
								</div>
								<div class="form-group row">
									<div class="col-sm-4 text-right">
										<label for="icon" path="icon">Icon</label>
									</div>
									<div class="col-sm-8">
										<input type="text" name="icon"
										id="icon" class="form-control" path="icon"/>
									</div>
									 
								</div>
								<div class="form-group">
									<input type="hidden" id="menuId" name="menuId" path="menuId" />
									<input type="hidden" id="menuType" name="menuType" path="menuType" />
								</div>
							</form:form>							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onClick="ValidateUpdateMenu()">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>