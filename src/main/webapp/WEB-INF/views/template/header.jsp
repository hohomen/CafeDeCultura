<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta charset="UTF-8">
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link href="/resources/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="/resources/css/custom.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/business-frontpage.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/earlyaccess/jejuhallasan.css"
	rel="stylesheet">
<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script type="text/javascript"
	src="/resources/bootstrap/js/bootstrap.min.js"></script>
<title>Cafe De Cultura</title>
</head>

<body>
	<!-- 네비게이션 바 -->
	<nav class="navbar navbar-expand-md navbar-dark  fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/home">Cafe De Cultura</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<!-- <li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span></a>
					</li> -->
					<li class="nav-item"><a class="nav-link" data-toggle="modal"
						href="#bs-example-modal-sm" id="login">News</a></li>
					<sec:authorize access="!isAuthenticated()">
					    <li class="nav-item"><a class="nav-link" href="/login/loginForm">Login</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
					    <li class="nav-item"><a class="nav-link" href="/j_spring_security_logout">Logout</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</nav>

	<!--모달 알림창  -->
	<div class="modal fade" id="bs-example-modal-sm" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="mySmallModalLabel">알람 서비스</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>

				</div>
				<div class="modal-body">
					<p>죄송합니다. &nbsp;서비스 준비중입니다.</p>
				</div>
			</div>
		</div>
	</div>