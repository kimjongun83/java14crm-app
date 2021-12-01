<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<div class="page__header mb-0">
    <div class="container page__container">
        <div class="navbar navbar-secondary navbar-light navbar-expand-sm p-0">
            <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarsExample03" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" id="navbarsExample03">
                <ul class="nav navbar-nav flex">
                    <li class="nav-item">
                        <a class="nav-link active" href="<c:url value="<%=UrlConst.HOME %>" />">
                            Dashboard
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            Project
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">
                                Manage Project
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.PROJECT_ADD %>" />">
                                Create New Project
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.PROJECT_STAFF %>" />">
                                Manage Staffs
                            </a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="<%=UrlConst.TASK_DASHBOARD %>" />">Task</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            User
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.USER_DASHBOARD %>" />">
                                Manage User
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.USER_ADD %>" />">
                                Create User
                            </a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            Role
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.ROLE_DASHBOARD %>" />">
                               	Manage Role
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.ROLE_ADD %>" />">
                                Create Role
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
