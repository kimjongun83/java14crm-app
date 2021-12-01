<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>
		User Profile - CRM APP
	</title>
	<link type="text/css" href="<c:url value="/assets/css/vendor-flatpickr.css" />" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/assets/css/vendor-flatpickr.rtl.css" />" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/assets/css/vendor-flatpickr-airbnb.css" />" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/assets/css/vendor-flatpickr-airbnb.rtl.css" />" rel="stylesheet" />
</head>

<body>
<!-- Breadcrumb -->
            <div style="padding-bottom: calc(5.125rem / 2); position: relative; margin-bottom: 1.5rem;">
                <div class="bg-primary" style="min-height: 150px;">
                    <div class="d-flex align-items-end container page__container" style="position: absolute; left: 0; right: 0; bottom: 0;">
                        <div class="avatar avatar-xl">
                            <img src="<c:url value="/assets/images/avatars/tuanphan.jpg" />" alt="avatar" class="avatar-img rounded" style="border: 2px solid white;">
                        </div>
                        <div class="card-header card-header-tabs-basic nav flex" role="tablist">
                            <a href="#activity" class="active show" data-toggle="tab" role="tab" aria-selected="true">Activities</a>
                            <a href="#purchases" data-toggle="tab" role="tab" aria-selected="false">Task</a>
                            <a href="#emails" data-toggle="tab" role="tab" aria-selected="false">Emails</a>
                            <a href="#quotes" data-toggle="tab" role="tab" aria-selected="false">Quotes</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Breadcrumb -->

            <div class="container page__container">
                <!-- Page Content -->
                <div class="row">
                    <div class="col-lg-3">
                        <h1 class="h4 mb-1">Phan Thanh Tuấn</h1>
                        <p class="text-muted">@TuanPhan</p>
                        <p>CRM Admin Template</p>
                        <div class="text-muted d-flex align-items-center">
                            <i class="material-icons mr-1">location_on</i>
                            <div class="flex">Núi Võ Đang</div>
                        </div>
                        <div class="text-muted d-flex align-items-center">
                            <i class="material-icons mr-1">link</i>
                            <div class="flex"><a href="https://tuanphan.dev/">tuanphan.dev</a></div>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="tab-content">
                            <div class="tab-pane active" id="activity">


                                <div class="card">
                                    <div class="px-4 py-3">
                                        <div class="d-flex mb-1">
                                            <div class="avatar avatar-sm mr-3"> 
                                                <img src="<c:url value="/assets/images/avatars/tuanphan.jpg" />" alt="Avatar" class="avatar-img rounded-circle">
                                            </div>
                                            <div class="flex">
                                                <div class="d-flex align-items-center mb-1">
                                                    <strong class="text-15pt">Phan Thanh Tuấn</strong>
                                                    <small class="ml-2 text-muted">3 days ago</small>
                                                </div>
                                                <div>
                                                    <p>You can visit my page at <a href="https://tuanphan.dev/">https://tuanphan.dev/</a> 🔥</p>
                                                    <p><a href="">#tuanphan</a> <a href="">#2020countdown</a></p>
                                                </div>

                                                <div class="d-flex align-items-center">
                                                    <a href="" class="text-muted d-flex align-items-center decoration-0"><i class="material-icons mr-1" style="font-size: inherit;">favorite_border</i> 888</a>
                                                    <a href="" class="text-muted d-flex align-items-center decoration-0 ml-3"><i class="material-icons mr-1" style="font-size: inherit;">thumb_up</i> 71</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card">
                                    <div class="px-4 py-3">
                                        <div class="d-flex mb-1">
                                            <div class="avatar avatar-sm mr-3">
                                                <img src="<c:url value="/assets/images/avatars/tuanphan.jpg" />" alt="Avatar" class="avatar-img rounded-circle">
                                            </div>
                                            <div class="flex">
                                                <div class="d-flex align-items-center mb-1">
                                                    <strong class="text-15pt">Phan Thanh Tuấn</strong>
                                                    <small class="ml-2 text-muted">4 days ago</small>
                                                </div>
                                                <div>
                                                    <p>This is what photoshop can do
                                                </div>

                                                <a href="" class="card my-3 text-body decoration-0"> 
                                                    <img src="<c:url value="/assets/images/avatars/photoshopsaga.jpg" />" alt="image" class="rounded card-img-top">
                                                    <span class="card-footer d-flex flex-column">
                                                        <strong>Photoshop</strong>
                                                        <span>Uploaded by someone</span>
                                                    <span class="text-muted">tuanphan.dev</span>
                                                    </span>
                                                </a>

                                                <div class="d-flex align-items-center">
                                                    <a href="" class="text-muted d-flex align-items-center decoration-0"><i class="material-icons mr-1" style="font-size: inherit;">favorite_border</i> 2711</a>
                                                    <a href="" class="text-muted d-flex align-items-center decoration-0 ml-3"><i class="material-icons mr-1" style="font-size: inherit;">thumb_up</i> 1911</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
              </div>
</body>