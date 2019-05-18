<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="/resources/css/board.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.user.userId" var="authUserId"/>
</sec:authorize>
<script>

    var G_BOARD_ID = ${boardVO.boardId }; //게시글 번호    
    //Handlebars 댓글 작성자 확인    
    Handlebars.registerHelper("eqReplyer", function(replyer, block){
        var accum = '';
        if(replyer == "${authUserId}"){
            accum += block.fn();
        }
        return accum;
    });
</script>

<form role="form" method="post">
    <input type='hidden' name='boardId' value="${boardVO.boardId}">
    <input type='hidden' name='page' value="${cri.page}">
    <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
    <input type='hidden' name='searchType' value="${cri.searchType}">
    <input type='hidden' name='keyword' value="${cri.keyword}">    
</form>

<div class="container col-md-10 card border2 border-coffee"
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

<div class="container col-md-10 border2 border-coffee" style="margin-top: 2%; margin-bottom: 0px;">
    <textarea id='txtarea' readonly="readonly" style="width: 100%; height: 24em; border: 0; background: clear;" >${boardVO.content}</textarea>
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

<!-- 댓글 등록 -->
<div class="container col-md-10 card text-dark " style="padding: 1%; margin-bottom:2em; line-height: 100%;">
    <sec:authorize access="isAuthenticated()">
        <div class=row>
            <div class="col-md-11">
                <textarea id = "newReplyText" name="replytext" rows="3" placeholder="댓글을 입력해 주세요." class="form-control"></textarea>
            </div>
            <div class="col-md-1" style="padding-top: 38px">                        
                    <input type="hidden" name="replyer" id='newReplyWriter' value="${authUserId }">
                    <input id="replyAddBtn" class="btn btn-coffee" Type='Submit' Value='등록'></input>         
            </div>
        </div>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <div class=row>
            <div class="col-md-12">
                <textarea id = "newReplyText" name="replytext" rows="3" placeholder="로그인 후 이용해 주세요." class="form-control"></textarea>
            </div>
        </div>    
    </sec:authorize>
</div>

<!-- 댓글 출력 -->
<div id="replies" class="container col-md-10">    
    <script id="template" type="text/x-handlebars-template">
         {{#each .}}    
             <div class="card" id="{{replyId}}">
                 <div class="card-body">
                     <div class="row" >                
                         <div class="col-md-12">
                            <p>
                                <strong>{{nickname}}</strong>
                            </p>
                            <div class="clearfix"></div>
                            <pre class=pretag >{{replytext}}</pre>
                            {{#eqReplyer replyer}}
                                <button type="submit" id="replyDelBtn" class="btn btn-brown float-right ml-1" >삭제</button>                        
                                <button type="submit" id="replyModBtn" class="btn btn-coffee float-right ">수정</button>
                            {{/eqReplyer}}
                         </div>
                     </div>
                 </div>
             </div>
         {{/each}}
    </script> 
</div>

<script src="/resources/js/boardRead.js"></script>
<%@ include file="../template/footer.jsp"%>