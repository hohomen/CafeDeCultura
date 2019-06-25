<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	  name='viewport'>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/business-frontpage.css" rel="stylesheet" type="text/css" />
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	  rel="stylesheet" type="text/css" />	  
<link href="http://fonts.googleapis.com/earlyaccess/jejuhallasan.css" rel="stylesheet">
<link href="/resources/css/custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<!-- <li class="nav-item">
					   <a class="nav-link" data-toggle="modal"
                            href="#bs-example-modal-sm" id="login">수다방</a>
                    </li> -->
					<sec:authorize access="!isAuthenticated()">
					    <!-- <li class="nav-item">
					        <a class="nav-link" data-toggle="modal" href="#bs-example-modal-sm" id="login">알람
					           <span class="badge badge-danger badge-counter">3+</span>
					        </a>
					    </li> -->
					    <li class="nav-item">
					        <a class="nav-link" href="/user/loginForm">로그인이 필요합니다.</a>
					    </li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">                  
                        <li class="nav-item dropdown">                         
                           <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">                               
                               <sec:authentication property="principal.user.nickname" var="authNickname"/>
                               <c:out value="${authNickname}"/>                               
                           </a>                        
                          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <div class="dropdown-item" id="userInfo">정보 수정</div>
                              <a class="dropdown-item" href="/home?searchType=nickname&keyword=${authNickname}">작성 글 보기</a>
                              <!-- <a class="dropdown-item" href="#">작성 댓글 보기</a> -->
                          </div>                        
                        </li>
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
	<sec:authorize access="isAuthenticated()">
	    <sec:authentication property="principal.user.userId" var="authUserId"/>
	</sec:authorize>
	<form name="userInfo" action="/user/userInfo" method="post">
	    <input type="hidden" name='userId' value="${authUserId}">
	</form>
<script>
	$("#userInfo").on("click", function(){
		$("form[name='userInfo']").submit();
	});
	
	// footer.jsp에서 이클립스 관련 인코딩 문제로 상단에 설정.
    function checkBrowser(){
        let agent = navigator.userAgent.toLowerCase();
        let msFlag = false;
        if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
            msFlag = true;
        }
        if ( agent.indexOf("edge") != -1 ) {
            msFlag = true;
        }
    
        if ( msFlag ) {        
            alert('지원하지 않는 브라우저 입니다. \n크롬 또는 웨일을 이용 해주세요.');
            $('body').html('');
        }   
    }
    $(document).ready(function(){
        checkBrowser();
    });
</script>