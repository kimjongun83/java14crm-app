<%@page import="cybersoft.java12.crmapp.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>Add Project Staff</title>
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
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD%>" />">Project</a></li>
							<li class="breadcrumb-item active" aria-current="page">Add
								Project Staff</li>
						</ol>
					</nav>
					<h1 class="m-0">Add Project Staff</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
			<div class="row no-gutters">
				<div class="col-lg-4 card-body">
					<p>
						<strong class="headings-color">Rules</strong>
					</p>
					<p class="text-muted">There is no rule!</p>
				</div>
				<div class="col-lg-8 card-form__body card-body">
					<form action="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD%>" />"
						method="post">
						<div class="form-group">
							<label for="project_id">ID:</label> <input type="text" name="project_id"
								value="${project.id }" readonly class="form-control" id="project_id">
						</div>
						<div class="form-group">
							<label for="name">Project Name:</label> <input type="text"
								name="name" value="${project.name }" readonly class="form-control"
								id="name">
						</div>
						<div class="form-group">
							<label for="join_date">Join_date:</label> <input type="date"
								class="form-control" name="join_date" id="join_date" value="">
						</div>
						<div class="form-group">
							<label for="staff">Staff:</label> <select id="staff" name="staff"
								data-toggle="select" class="form-control">
								<c:forEach var="user" items="${users }">
									<option value="${user.id }">${user.name }</option>
								</c:forEach>
							</select>
						</div>

						<button type="submit" class="btn btn-primary">Add</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>