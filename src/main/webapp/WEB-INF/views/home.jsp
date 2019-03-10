<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./template/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>


<!-- Header with Background Image -->
<header class="business-header">
	<div class="container">
		<div class="row">
			<div class="col-lg-12"></div>
		</div>
	</div>
</header>

<!-- Header with phrase and search bar -->
<div class='row ' style="padding-bottom: 3%; padding-top: 1%;">
	<div class="col-md-7 header2"
		style="text-align: center; padding: 10px;">
		<h2 style="color: c0392b;">
			<strong>문화인을 위한 공간</strong>
		</h2>
	</div>
	<div class="col-md-5 header2"
		style="text-align: center; padding: 10px;">
		<a href='/board/register'><button class="btn btn-info">
				<h5>글쓰기</h5>
			</button></a>
		<!-- <div class="form-group">
            <input type="text" class="form-control" placeholder="검색"><button type="submit" class="btn btn-default">Submit</button>
        	</div> -->
	</div>
</div>

<div class="container col-md-9">
	<div class=row style="height: 10px;">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="서비스 준비중 입니다.">
		</div>
		<div class="form-group col-md-6">
			<button type="submit" class="btn btn-default">찾기</button>
		</div>

		<div class="form-group col-md-2" style="float: right ">
			<button type="submit" class="btn btn-default">글쓰기</button>
		</div>
	</div>
</div>
<br><br>

<!-- Board lists -->
<div class="container col-md-10 ">
	<c:forEach items="${list}" var="boardVO">
		<a href='/board/read?board_id=${boardVO.board_id}'>
			<div class="card border border-success">
				<div class="text-dark card-body">
					<h5>
						&nbsp;${boardVO.title} <span class="text-danger">[${boardVO.view_cnt}]</span>
					</h5>
					&nbsp; ${boardVO.member_id}&nbsp; <small class="text-muted"><fmt:formatDate
							pattern="yyyy-MM-dd HH:mm" value="${boardVO.reg_date}" /> </small>
				</div>
			</div>
		</a>
	</c:forEach>
</div>

<%@ include file="./template/footer.jsp"%>