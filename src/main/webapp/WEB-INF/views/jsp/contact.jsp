<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="form-address-edit">
	<div class="modal fade" id="contact-edit-modal" tabindex="-1"
		role="dialog" aria-labelledby="editContactModal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="ml-5">Editing Contact</h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row border-btm">
							<form action="/adpostm/user/update/contact" 
								method="post" class="form-horizontal w-100 " 
									id="edit-contact-form">
								<div class="form-group row has-danger">
									<div class="col-sm-12">
										<label for="mobileNo">Mobile No
											<input type="text" name="mobileNo"
												id="mobileNo" class="form-control" />
										</label>
										<div class="error-div">
											<label id="mobileNo-error" class="form-control-danger"
											 	for="mobileNo"></label>
										</div>
									</div>
								
								</div>
								<input type="hidden" name="co-userId" id="co-userId"/>
							</form>							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="submitContactEdit">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>