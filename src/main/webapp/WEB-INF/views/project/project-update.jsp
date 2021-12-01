<%@page import="cybersoft.java12.crmapp.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta charset="UTF-8">
<title>Project Update</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Project  Update
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0"> Project Update</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
            <div class="row no-gutters">
                <div class="col-lg-4 card-body">
                    <p><strong class="headings-color">Rules</strong></p>
                    <p class="text-muted">There is no rule!</p>
                </div>
                <div class="col-lg-8 card-form__body card-body">
                    <form action="<c:url value="<%=UrlConst.PROJECT_UPDATE %>" />" method="post">
                    	<div class="form-group">
                            <label for="id">ID:</label>
                            <input type="text" name="id" value="${project.id }" readonly class="form-control" id="id">
                        </div>
                        <div class="form-group">
                            <label for="name">Project Name:</label>
                            <input type="text" name="name" value="${project.name }" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                            <label for="description">Project Description:</label>
                            <input type="text" name="description" value="${project.description }" class="form-control" id="description">
                        </div>
                         <div class="form-group">
                            <label for="description">Project Start Date:</label>
                            <input type="date" name="startDate" value="${project.startDate }" class="form-control" id="startDate">
                        </div>
                         <div class="form-group">
                            <label for="description">Project End Date:</label>
                            <input type="date" name="endDate" value="${project.endDate }" class="form-control" id="endDate">
                        </div>                     
                        <div class="form-group">
                                <label for="owner">Owner</label>
                                <select id="owner" name="owner" data-toggle="select" class="form-control">
                                    <c:forEach var="user" items="${users }">
									<c:choose>
										<c:when test="${project.owner.id == user.id}">
											<option value="${user.id }" selected="">${user.name }</option>
										</c:when>

										<c:otherwise>
											<option value="${user.id }">${user.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
                                </select>
                        </div>
                       
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </div>
     </div>
</body>