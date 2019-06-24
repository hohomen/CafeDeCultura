<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

<div class="jumbotron">
	<h2>
		<strong>&nbsp;글 수정</strong>
	</h2>
</div>

<div class="row container card form-group border-coffee" style="padding: 1%; margin: 2%">
    <form name='write_form' role="form" method='post' style="margin-top: 1%; margin: 2%">
        <input type='hidden' name='page' value="${cri.page}">
        <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
        <input type='hidden' name='searchType' value="${cri.searchType}">
        <input type='hidden' name='keyword' value="${cri.keyword}"> 
        <input type="hidden" name="boardId" value="${boardVO.boardId}">    
           
        <div class="form-check-inline form-group ">
            <div class=" text-dark">
                <strong>
                    <label for="member_name">작성자</label>
                </strong>               
                <input type="text" class="form-control border-coffee" id="member_name"
                       name="userId" value="${boardVO.nickname}" readonly="readonly">
            </div>&nbsp; &nbsp;         
        </div>
        <div class="form-group text-dark">
            <strong>
                <label for="write_time">제목</label>
            </strong>
            <div class="form-check-inline col-xl-12">
                <input type='text' id="write_time" class="form-control border-coffee" 
                       placeholder="제목을 입력하세요" name='title' value='${boardVO.title}'>
            </div>
        </div>
        <div class=" text-dark form-group">
            <textarea id="summernote" class="form-control border-coffee" 
                      placeholder="내용을 입력하세요" name="content" required>${boardVO.content}</textarea>            
        </div>
        <div class="form-group">       
            <input class="btn btn-coffee pull-right" type="reset" value="다시 쓰기">
            <button id ="modifyWriteBtn" class="btn btn-coffee">수정</button>
            <button id ="modifyCancelBtn" class="btn btn-brown">취소</button>                 
        </div>
    </form>
</div>

<script src="/resources/js/board/modify.js"></script>
<script src="/resources/js/board/wrtie.js"></script>
<%@ include file="../template/footer.jsp"%>