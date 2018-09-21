<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="form-menu-edit">
	<div class="modal fade" id="advert-detail-modal" tabindex="-1"
		role="dialog" aria-labelledby="advertDetailModal" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h1>
						<span id="spanHeading"></span>
					</h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-sm-8 nopadding">
								<img id="lg-img" width="100%"/>
							</div>
							<div class="col-sm-4">
								<img id="sm-img1" width="100%"/>
								<img id="sm-img2" width="100%"/>
							</div>					
						</div>
						<div class="row font-weight-bold grey-text">
							<span id="spanUser"></span>
						</div>
						<div class="row font-weight-bold grey-text">
							<span id="spanSubmitDate"></span>				
						</div>
						<br />
						<div class="row">
							<p><span id="spanBody"></span></p>	
						</div>
						<div class="row font-weight-bold grey-text">
							<span id="spanContactEmail"></span>
						</div>
						<div class="row font-weight-bold grey-text">
							<span id="spanContactPhone"></span>			
						</div>
					</div>
				</div>
				<div class="modal-footer">
				<!-- 
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onClick="ValidateUpdateMenu()">Save</button>
				-->
				</div>
			</div>
		</div>
	</div>
</div>