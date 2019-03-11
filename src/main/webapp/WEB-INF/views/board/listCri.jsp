<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
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
<div class='row' >
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
	</div>
</div>

<div class="container col-md-10">
	<div class=row style="height: 10px; padding:3%;">
		<div class="col-md-8">
		<form class="form-inline">
			<div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button"
                        id="dropdownMenuButton" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">선택</button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">Action</a> 
                        <a class="dropdown-item" href="#">Another action</a> 
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </div>
			
			<div class="form-group">
				<input type="text" class="form-control" placeholder="서비스 준비중 입니다.">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">찾기</button>
			</div>
		</div>
		</form>

		<div class="form-group col-md-4" >
			<button type="submit" class="btn btn-default pull-right">글쓰기</button>
		</div>
	</div>


<!-- Board lists -->

	<c:forEach items="${list}" var="boardVO">
		<a href='/board/read?board_id=${boardVO.board_id}'>
			<div class="card border1 border-success">
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


<!-- 페이징 -->

<div class="box-footer">
	<div class="text-center">
		<ul class="pagination">

			<c:if test="${pageMaker.prev}">
				<li class="page-item"><a
					href="listCri${pageMaker.makeQuery(pageMaker.startPage - 1) }"
					class="page-link">&laquo;</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage }"
				end="${pageMaker.endPage }" var="idx">
				<li class="page-item"
					<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
					<a href="listCri${pageMaker.makeQuery(idx)}" class="page-link">${idx}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li class="page-item"><a
					href="listCri${pageMaker.makeQuery(pageMaker.endPage +1) }"
					class="page-link">&raquo;</a></li>
			</c:if>

		</ul>
	</div>
</div>

</div>

<%@ include file="../template/footer.jsp"%>