<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<div class="" id="search-bar" class="clearfix">
		 
		<a class="float-left" href="/adpostm/home" >
			
		</a>
			<form id="form-search" action="/adpostm/advert/search" method="get" class="form-inline float-right">
					<input id="search" class = "form-control" placeholder="search"  name="search" value="${search}"/>
					<button type="submit" class="button-primary btn" role="button" onClick="SubmitSearch()">
						<img src="<%=request.getContextPath()%>/resources/images/magnifier.png" 
							alt="search" title="search" 
							width="20" height="20" />
					</button>
			</form>	
			
	</div>
<div class="clear"></div>