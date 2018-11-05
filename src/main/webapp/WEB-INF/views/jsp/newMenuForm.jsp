<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="form-menu-add">
	<div class="modal" id="menu-add-modal" tabindex="-1" role="dialog"
		aria-labelledby="addMenuModal" aria-hidden="true">
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
					<div class="container border-btm">
						<div class="row">
							<form action="/adpostm/menus/add" method="post"
								class="form-horizontal w-75" id="add-menu-form">
								<div class="form-group row">
									<div class="col-sm-7">
										<label for="addParentId">Parent Menu 
											<select name="addParentId" id="addParentId"
												class="form-control  text-capitalize">
											</select>
										</label>
									</div>
									<div class="col-sm-5">
										<label for="addMenuType">Menu Type
											<select name="menuTypeSelect" id="addMenuType"
												class="form-control  text-capitalize w-100">
											</select>
										</label>
									</div>
								</div>
								<div class="form-group row has-danger">
									<div class="col-sm-7">
										<label for="addMenuName">Menu Name <input type="text"
											name="addMenuName" id="addMenuName" class="form-control" />
										</label>
										<div class="error-div">
											<label id="addMenuName-error" class="form-control-danger"
												for="addMenuName"></label>
										</div>
									</div>
									<div class="col-sm-5 ">
										<label for="addMenuDesc">Description <input type="text"
											name="addMenuDesc" id="addMenuDesc" class="form-control" />
										</label>
									</div>

								</div>
								<div class="form-group row has-danger">
									<div class="col-sm-12">
										<label for="addIcon">Icon <input type="text" name="addIcon"
											id="addIcon" class="form-control" />
										</label>
									</div>
								</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-primary"
					onclick="ValidateAddMenu()">Save</button>
			</div>
		</div>
	</div>
</div>
</div>