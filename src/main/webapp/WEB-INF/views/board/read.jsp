<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	/*수정, 삭제 페이지 이동  */
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-success").on("click", function() {
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});

		$(".btn-info").on("click", function() {
			formObj.attr("action", "/board/remove");
			formObj.submit();
		});		

	});
</script>


<form role="form" method="post">
	<input type='hidden' name='board_id' value="${boardVO.board_id}">
</form>


<div class="container col-md-10 card border2 border-success"
	style="margin-top: 2%;">
	<div style="margin: 1%; margin-left: 1%; margin-top: 2%">
		<div class="row ">
			<h5 class="text-dark col-md-9">
				<strong> ${boardVO.member_id} > </strong> ${boardVO.title }
			</h5>
			<div class="col-md-3">
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${boardVO.reg_date}" />
			</div>
		</div>
	</div>
</div>


<div class="container col-md-10 border2 border-success"
	style="margin-top: 2%; margin-bottom: 0px;">

	<textarea id='txtarea' 
		style="width: 100%; height: 24em; border: 0;  background: clear;"
		placeholder="내용을 입력하세요" id="content" name="content"
		readonly="readonly">${boardVO.content}
	</textarea>
</div>


<div class="container col-md-10 "
	style="margin-bottom: 4%; margin-top: 10px;">
	<div class="box-footer">
		<button type="submit" class="btn btn-success">수정</button>
		<button type="submit" class="btn btn-info">삭제</button>
	</div>
</div>


<!-- 댓글 출력 -->
<div class="container col-md-10 card text-dark "
	style="padding: 1%; line-height: 160%;">
	<div class=row>
		<div class="col-md-10">
			<textarea rows="3" placeholder="댓글 서비스는 준비중입니다." class="form-control"></textarea>
		</div>
		<div class="col-md-2" style="padding-top: 38px">
			<input class="btn btn-success" Type='Submit' Value='등록'></input>
		</div>
	</div>
</div>

<%@ include file="../template/footer.jsp"%>