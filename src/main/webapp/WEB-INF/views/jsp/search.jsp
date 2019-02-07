<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List, java.util.Iterator,
    java.text.SimpleDateFormat, java.util.Date, com.adpostm.domain.model.Menu,
    java.util.Date"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/views/jspinc/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/jspinc/header.jsp" />

<div id="pg-search">
	<div class="container mt-5">
		<div class="row mb-5">
			<div class="col-sm-12">
				<jsp:include page="/WEB-INF/views/jspinc/searchbar.jsp" />
				<p>${searchMsg}</p>
			</div>
		</div>
		<div class="row">
			<!-- Filters -->
			<div class="col-sm-3">
				<div id="filter-accordion" role="tablist"
					aria-multiselectable="true" class="text-capitalize">
					<div class="card">
						<!-- CATEGORY -->
						<div class="card-header" role="tab">
							<h5 class="mb-0">
								<a data-toggle="collapse" href="#categories"
									aria-expanded="true" aria-controls="categories"> CATEGORY </a>
							</h5>
						</div>

						<c:if test="${fn: length(categories) > 0}">
							<div id="categories" class="collapse" role="tabpanel"
								aria-labelledby="categories" data-parent="#filter-accordion">
								<div class="card-block">
									<div id="sub-accordion" role="tablist"
										aria-multiselectable="true" class="text-capitalize">
										<c:forEach items="${categories}" var="category"
											varStatus="loop">
											<div class="card">
												<!-- CATEGORY -->
												<div class="card-header" role="tab">
													<h5 class="mb-0">
														<a data-toggle="collapse" href="#sub${loop.index}"
															aria-expanded="true" aria-controls="sub${loop.index}">
															${category.getMenuName()} </a>

													</h5>
												</div>
												<div id="sub${loop.index}" class="collapse" role="tabpanel"
													aria-labelledby="sub${loop.index}"
													data-parent="#sub-accordion">
													<div class="card-block">
														<ul class="list-unstyled">
															<c:forEach items="${category.getSubMenu()}" var="submenu"
																varStatus="subloop">
																<li>${submenu.getMenuName()}<input type="checkbox"
																	name="category" class="filterCheck float-right"
																	onchange="PerformSearchFilter()" class="round" 
																	<c:forEach items="${fCategory}" var="fCat">
																		<c:if test="${submenu.getMenuName() eq fCat}">
																			checked="checked"
																		</c:if>
																	</c:forEach>
																	
																	/>
																</li>
																<li class="dropdown-divider"></li>
															</c:forEach>
														</ul>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<div class="card">
						<!-- LOCATION FILTER -->
						<div class="card-header" role="tab">
							<h5 class="mb-0">
								<a data-toggle="collapse" data-parent="#filter-accordion"
									href="#locations" aria-expanded="false"
									aria-controls="locations"> LOCATION </a>
							</h5>
						</div>
						<c:if test="${fn: length(locations) > 0}">
							<div id="locations" class="collapse" role="tabpanel"
								aria-labelledby="locations">
								<div class="card-block">
									<ul class="list-unstyled">
										<c:forEach items="${locations}" var="location"
											varStatus="loop">
											<li>${location}<input type="checkbox" name="location"
												class="filterCheck float-right"
												onchange="PerformSearchFilter()" 
												<c:forEach items="${fLocation}" var="fLoc">
													<c:if test="${location eq fLoc}">
														checked="checked"
													</c:if>
												</c:forEach>
												
												/>
											</li>
											<li class="dropdown-divider"></li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</c:if>
					</div>
					<div class="card">
						<!-- YEARS FILTER -->
						<div class="card-header" role="tab">
							<h5 class="mb-0">
								<a data-toggle="collapse" data-parent="#filter-accordion"
									href="#years" aria-expanded="false" class="collapsed"
									aria-controls="years"> YEAR </a>
							</h5>
						</div>
						<c:if test="${fn: length(years) > 0}">
							<div id="years" class="collapse" role="tabpanel"
								aria-labelledby="years">
								<div class="card-block">
									<ul class="list-unstyled">
										<c:forEach items="${years}" var="year" varStatus="loop">
											<li>${year}<input type="checkbox" name="year"
												class="filterCheck float-right"
												onchange="PerformSearchFilter()" 
												<c:forEach items="${fYear}" var="fYe">
													<c:if test="${year eq fYe}">
														checked="checked"
													</c:if>
												</c:forEach>
												/>
											</li>
											<li class="dropdown-divider"></li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</c:if>
					</div>
					<div class="card">
						<!-- IMAGES FILTER -->
						<div class="card-header" role="tab">
							<h5 class="mb-0">
								<a data-toggle="collapse" data-parent="#filter-accordion"
									href="#images" aria-expanded="false" class="collapsed"
									aria-controls="images"> IMAGES </a>
							</h5>
						</div>
						<c:if test="${fn: length(years) > 0}">
							<div id="images" class="collapse" role="tabpanel"
								aria-labelledby="images">
								<div class="card-block">
									<ul class="list-unstyled">
										<li>Images Only <input type="checkbox" name="images"
											class="filterCheck float-right"
											onchange="PerformSearchFilter()" class="round" 
											<c:if test="${fImage eq true}">
												checked=""checked"
											</c:if>
											/>
										</li>
									</ul>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<c:if test="${fn: length(advertList) > 0}">
					<c:forEach var="advert" items="${advertList}">
						<h6 class="text-uppercase text-left  font-weight-bold">
							<a href="/adpostm/advert/detail?id=${advert.getAdvertId()}"
								class="ad-dtl-link title"> ${advert.advertDetail.getTitle()}</a>
						</h6>
						<p class="text-capitalize">${advert.advertDetail.getLocation()}</p>
						<br />
						<p class="text-capitalize">${advert.advertDetail.getBody()}</p>

						<hr />
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/jspinc/footer.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/jsp/postDetail.jsp"></jsp:include>
