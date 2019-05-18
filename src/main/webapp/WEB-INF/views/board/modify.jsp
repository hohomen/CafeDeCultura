<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>

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
            <textarea class="form-control border-coffee" rows="20"
                      style="margin-top: 0px; margin-bottom: 0px; height: 20rem;" cols="90" 
                      placeholder="내용을 입력하세요" name="content" id="d_content">${boardVO.content}</textarea>
        </div>
    </form>
        
    <div class="form-group">       
        <input class="btn btn-coffee pull-right" type="button" onClick="Reset()" value="다시 쓰기">
        <button id ="modifyWriteBtn" class="btn btn-coffee">수정</button>
        <button id ="modifyCancelBtn" class="btn btn-brown">취소</button>                 
    </div>    
</div>

<script>
    //다시 작성
    function Reset() {
                write_form.title.value = "";
                write_form.content.value = "";
    }
    
    //수정, 취소
    $(document).ready(function() {
        var formObj = $("form[role='form']");
        console.log(formObj);                
        $("#modifyCancelBtn").on("click", function(){
            self.location = "/board/read?page=${cri.page}&perPageNum=${cri.perPageNum}"
                            +"&searchType=${cri.searchType}&keyword=${cri.keyword}"
                            +"&boardId=+${boardVO.boardId}";
        });
        $("#modifyWriteBtn").on("click", function(){
            formObj.submit();
        });
    });
</script>
<%@ include file="../template/footer.jsp"%>