<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
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
							<li class="breadcrumb-item active" aria-current="page">User
								Dashboard</li>
						</ol>
					</nav>
					<h1 class="m-0">User Dashboard</h1>
				</div>
				<div class="ml-auto">
					<a href="<c:url value="<%=UrlConst.USER_ADD%>" />"
						class="btn btn-light"><i
						class="material-icons icon-16pt text-muted mr-1">add</i> Add New
						User </a>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->

	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
			<div class="search-form search-form--light m-3">
				<input type="text" class="form-control search" placeholder="Search">
				<button class="btn" type="button">
					<i class="material-icons">search</i>
				</button>
			</div>
			<table class="table mb-0 thead-border-top-0">
				<thead>
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Role</th>
						<th>Phone</th>
						<th>#</th>
					</tr>
				</thead>
				<tbody class="list" id="staff02">
					<c:choose>
						<c:when test="${users == null}">
							<tr class="row">
								<td class="col-12 text-center">There is no data.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.name }</td>
									<td>${user.email }</td>
									<td><span class="badge badge-primary">${user.role.name }</span></td>
									<td>${user.phone }</td>
									<td><a
										href="<c:url value="<%=UrlConst.USER_UPDATE%>" />?id=${user.id}"
										class="text-muted"><i class="material-icons">settings</i></a>
										<a
										href="<c:url value="<%=UrlConst.USER_DELETE%>" />?id=${user.id}"
										class="text-muted"><i class="material-icons">delete</i></a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- END BODY -->
</body>