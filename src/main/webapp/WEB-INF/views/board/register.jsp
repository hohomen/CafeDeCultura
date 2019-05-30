<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

<div class="jumbotron">
	<h2>
		<strong>&nbsp;글 작성</strong>
	</h2>
</div>

<!-- 작성 form -->
<div class="row container card form-group border-coffee"
	style="padding: 1%; margin: 2%">
	<form name='write_form' method='post' style="margin-top: 1%; margin: 2%">	
	    <input type="hidden" name="userId" value="<sec:authentication property="principal.user.userId"/>" />	
		<div class="form-check-inline form-group ">
			<div class=" text-dark">
				<strong>
				    <label for="member_name">작성자</label>
				</strong>				
				<input type="text" id="nickname" name="nickname" value="<sec:authentication property="principal.user.nickname"/>" 
				       readonly="readonly" class="form-control border-coffee" >
			</div>&nbsp; &nbsp;			
		</div>
		<div class="form-group text-dark">
			<strong>
			    <label for="write_time">제목</label>
			</strong>
			<div class="form-check-inline col-xl-12">
				<input type='text' id="write_time" class="form-control border-coffee" 
				       placeholder="제목을 입력하세요" name='title' required>
			</div>
		</div>
		<div class=" text-dark form-group">
			<textarea id="summernote" class="form-control border-coffee" 
			          placeholder="내용을 입력하세요" name="content" required></textarea>
		</div>
		<div class="form-group">
			<input class="btn btn-brown" type="submit" value="작성"> &nbsp;
			<input class="btn btn-coffee" type="button" onClick="Reset()" value="다시 쓰기">
		</div>
	</form>
</div>

<script src="/resources/js/board/wrtie.js"></script>
<%@ include file="../template/footer.jsp"%>