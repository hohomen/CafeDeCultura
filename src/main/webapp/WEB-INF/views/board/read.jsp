<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<link href="/resources/css/board.css" rel="stylesheet" type="text/css" />
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.user.userId" var="authUserId"/>
</sec:authorize>
<input type='hidden' name='userId' value="${authUserId}">
<form role="form" method="post">
    <input type='hidden' name='boardId' value="${boardVO.boardId}">
    <input type='hidden' name='page' value="${cri.page}">
    <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
    <input type='hidden' name='searchType' value="${cri.searchType}">
    <input type='hidden' name='keyword' value="${cri.keyword}">
</form>

<div class="container col-md-10 card border4 border-coffee"
    style="margin-top: 2%;">
    <div style="margin: 1%; margin-left: 1%; margin-top: 2%">
        <div class="row ">
            <h5 class="text-dark col-md-9">
                <strong> ${boardVO.nickname} > </strong> ${boardVO.title }
            </h5>
            <div class="col-md-3">
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                    value="${boardVO.regDate}" />
            </div>
        </div>
    </div>
</div>

<div class="container col-md-10 border4 border-coffee" style="margin-top: 2%; margin-bottom: 0px; padding:2%;">
${boardVO.content}
</div>

<div class="container col-md-10 " style="padding-bottom: 1%; margin-bottom: 4%; margin-top: 10px;">
    <div class="box-footer">
        <button type="submit" id ="listPage" class="btn btn-coffee pull-right">목록으로</button>
        <sec:authorize access="isAuthenticated()">
            <c:if test="${authUserId eq boardVO.userId }">
                <button type="submit" id ="writeModifyBtn" class="btn btn-coffee">수정</button>
                <button type="submit" id ="writeAddBtn" class="btn btn-brown">삭제</button> 
            </c:if>
        </sec:authorize>
    </div>
</div>
<div id="replyDialog" style="width: 99%; display:none">
    <form name="form3" action="board6ReplySave" method="post">
        <input type="hidden" name="boardId" value="<c:out value="${boardVO.boardId}"/>"> 
        <input type="hidden" name="replyId"> 
        <input type="hidden" name="reParent"> 
        작성자: <input type="text" name="replyer" size="20" maxlength="20"> <br/>
        <textarea name="replyText" rows="3" cols="60" maxlength="500"></textarea>
        <a href="#" onclick="fn_replyReplySave()">저장</a>
        <a href="#" onclick="fn_replyReplyCancel()">취소</a>
    </form>
</div>

<!-- 댓글 등록 -->
<div class="container col-md-10 card text-dark " style="padding: 1%; margin-bottom:2em; line-height: 100%;">
    <sec:authorize access="isAuthenticated()">
        <div class=row style="padding-left:1%;">            
            <textarea id = "newReplyText" name="replyText" rows="3" placeholder="댓글을 입력해 주세요." class="form-control"></textarea>
            <input type="hidden" name="replyer" id='newReplyWriter' value="${authUserId }">
            <input id="replyAddBtn" class="btn btn-coffee" Type='Submit' Value='등록'></input>        
            
        </div>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <div class=row>
            <div id="needToLogin">
                               로그인 후 이용해 주세요
            </div>
        </div>    
    </sec:authorize>
</div>

<div id="replies" class="container col-md-10">
    <c:forEach items="${replies}" var="replyVO">
        <c:if test="${replyVO.replyer ne 'none' }">      
            <div class="card" id="${replyVO.replyId}" >       
                <div class="row depth${replyVO.reDepth}">
                    <div class="iamgeBox">                                                                            
                        <img src="/file/displayFile?fileName=${replyVO.image}&pathType=userProfile" class="img img-rounded img-fluid" />
                    </div>
                    <div class="contentBox">                    
                        <strong>${replyVO.nickname}</strong>&nbsp; 
                        <small>${replyVO.regDate}</small>&nbsp;
                        <c:if test="${replyVO.replyer ne authUserId }">
                            <span id="${replyVO.reParent}"></span>
                            <span id="addReReply">답글</span></c:if>
                        <c:if test="${replyVO.replyer eq authUserId }">
                            <span id="replyModify">수정</span>&nbsp;
                            <span id="replyDelete">삭제</span>
                        </c:if>
                        <pre>${replyVO.replyText}</pre>
                    </div>
                </div>            
            </div>
        </c:if>
        <c:if test="${replyVO.replyer eq 'none' }">
            <div class="card" id="${replyVO.replyId}" >       
                <div class="row depth${replyVO.reDepth}">                    
                    <div class="contentBox">
                        <pre>${replyVO.replyText}</pre>
                    </div>
                </div>            
            </div>
        </c:if>
    </c:forEach>
</div>
<script src="/resources/js/board/read.js"></script>
<%@ include file="../template/footer.jsp"%>