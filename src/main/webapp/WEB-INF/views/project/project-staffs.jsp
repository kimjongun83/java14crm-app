<%@page import="cybersoft.java12.crmapp.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>Project Staff</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item"><a href="#">Staff</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Project Staff</li>
						</ol>
					</nav>
					<h1 class="m-0">Project Staff</h1>
				</div>

			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->

	<!-- START BODY -->
	<div class="container">
		<c:forEach var="project" items="${projects }">
			<div>
				<div class="d-flex align-items-center">
					<div>
						<h3>${project.name}</h3>
					</div>
					<div class="ml-auto">
						<a
							href="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD%>" />?id=${project.id}"
							class="btn btn-light mb-3"><i
							class="material-icons icon-16pt text-muted mr-1">add</i> Add New
							Member To Project</a>
					</div>
				</div>
				<div class="card card-form">
					<div class="row no-gutters">

						<table class="table mb-0 thead-border-top-0">
							<thead>
								<tr>
									<th>Project ID</th>
									<th>Project Name</th>
									<th>User ID</th>
									<th>User Name</th>
									<th>Join Date</th>
									<th>#</th>

								</tr>
							</thead>
							<tbody class="list" id="staff02">
								<c:choose>
									<c:when test="${projectUsers == null }">
										<tr class="row">
											<td class="col-12">There is no data</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="projectUser" items="${projectUsers }">
											<c:if test="${projectUser.project.id == project.id }">
												<tr>
													<td>${projectUser.getProject().getId() }</td>
													<td>${projectUser.getProject().getName() }</td>
													<td>${projectUser.getUser().getId() }</td>
													<td>${projectUser.getUser().getName() }</td>
													<td>${projectUser.joinDate }</td>
													<td><a
														href="<c:url value="<%=UrlConst.PROJECT_STAFF_REMOVE %>" />?id=${projectUser.user.id }"
														class="text-muted"><i class="material-icons">delete</i></a></td>

												</tr>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- END BODY -->
</body>