<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="form-address-edit">
	<div class="modal fade" id="address-edit-modal" tabindex="-1"
		role="dialog" aria-labelledby="editAddressModal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="ml-5">Editing Address</h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row border-btm">
							<form:form action="/adpostm/user/update/address" 
								method="post" class="form-horizontal w-100 " 
									id="edit-address-form" >
								<div class="form-group row">
									<div class="col-sm-12">
										<label for="country" class="w-50">Country
											<select name="country"
												id="country" class="form-control  text-capitalize ">
											</select>
										</label>
									</div>
								</div>
								<div class="form-group row has-danger">
									<div class="col-sm-6">
										<label for="address1">Address 1
											<input type="text" name="postAddress1"
												id="postAddress1" class="form-control" />
										</label>
										<div class="error-div">
											<label id="postAddress1-error" class="form-control-danger"
											 	for="postAddress1"></label>
										</div>
									</div>
									<div class="col-sm-6">
										<label for="postAddress2">Address 2
											<input type="text" name="postAddress2"
												id="postAddress2" class="form-control"/>
										</label>
										<div class="error-div">
											<label id="postAddress2-error" class="form-control-danger"
												 for="postAddress2"></label>
										</div>
									</div>
									 
								</div>
								<div class="form-group row has-danger">
									<div class="col-sm-4">
										<label for="surbub">City
												<input type="text" name="surbub"
											id="surbub" class="form-control"/>
										</label>
										<div class="error-div">
											<label id="surbub-error" class="form-control-danger"
											 	for="surbub"></label>
										</div>
									</div>
									<div class="col-sm-4">
										<label for="state">State/District
											<input type="text" name="state"
												id="state" class="form-control"/>
										</label>
										<div class="error-div">
											<label id="state-error" class="form-control-danger"
											 	for="state"></label>
										</div>
									</div>
									<div class="col-sm-4">
										<label for="postCode">Post Code
											<input type="text" name="postCode"
												id="postCode" class="form-control"/>
										</label>
										<div class="error-div">
											<label id="postCode-error" class="form-control-danger"
											 for="postCode"></label>
										</div>
									</div>
									 
								</div>
								<div class="form-group row has-danger">
									<div class="col-sm-4">
										<label for="mobileNo">Mobile No
											<input type="text" name="mobileNo"
												id="mobileNo" class="form-control"/>
										</label>
										<div class="error-div">
											<label id="mobile-error" class="form-control-danger"
											 for="mobile"></label>
										</div>
									</div>
								</div>
								<input type="hidden" name="userId" id="userId"/>
								<input type="hidden" name="userDetailId" id="userDetailId"/>
							</form:form>							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="submitAddressEdit">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>