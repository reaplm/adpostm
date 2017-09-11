<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="sidebar">
	<div id="user-info">
		<div class="user-info-img" >
			<img src="<%=request.getContextPath()%>/resources/images/samantha-lorette-335011_ed.jpg"
				width="100%" />
		</div>
		<div class="user-info-txt">
			<p>User Information</p>
			
		</div>
	</div>
	<div id="accordion" role="tablist" aria-multiselectable="true" >
		<div class="card">
			<div class="card-header" role="tab">
				<h5 class="mb-0">
					<a data-toggle="collapse" data-parent="#accordion"  
						href="#home" aria-expanded="true"  
						aria-controls="home">Home</a>
				</h5>
			</div>
			<div id="home" class="collapse show" role="tabpanel" 
				aria-labelledby="Home">
				<div class="card-block">
					<ul class="list-unstyled">
						<li><a href="#">Dashboard</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" role="tab" id="headingThree">
				<h5 class="mb-0">
					<a class="collapsed" data-toggle="collapse" data-parent="#accordion"  
						href="#account" aria-expanded="false"  
						aria-controls="account">My Account</a>
				</h5>
			</div>
			<div id="account" class="collapse" role="tabpanel" 
				aria-labelledby="account">
				<div class="card-block">
					<ul class="list-unstyled">
						<li><a href="#">Preferences</a></li>
						<li class="dropdown-divider"></li>
						<li><a href="#">Subscriptions</a></li>
						<li class="dropdown-divider"></li>
						<li><a href="#">My Posts</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" role="tab" id="headingTwo">
				<h5 class="mb-0">
					<a class="collapsed" data-toggle="collapse" data-parent="#accordion"  
						href="#manage" aria-expanded="false" 
						aria-controls="manage">Manage</a>
				</h5>
			</div>
			<div id="manage" class="collapse" role="tabpanel" 
				aria-labelledby="manage">
				<div class="card-block">
					<ul class="list-unstyled">
						<li><a href="#">Users</a></li>
						<li class="dropdown-divider"></li>
						<li><a href="/adpostm/menus">Menus</a></li>
						<li class="dropdown-divider"></li>
						<li><a href="#">Posts</a></li>
					</ul>
				</div>
			</div>
		</div>
		
	</div>
</div>
