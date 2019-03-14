<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
<!-- Custom styles for this template -->
<link href="/resources/css/custom.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/business-frontpage.css" rel="stylesheet" type="text/css"/>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
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
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<!-- <li class="nav-item"><a class="nav-link" href="#">News</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Setting</a>
					</li> -->
					<li class="nav-item"><a class="nav-link" data-toggle="modal"
						href="#bs-example-modal-sm" id="login">Login</a></li>
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
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="mySmallModalLabel"></h4>
				</div>
				<div class="modal-body">
					<p>죄송합니다. &nbsp;서비스 준비중입니다.</p>
				</div>
			</div>
		</div>
	</div>
