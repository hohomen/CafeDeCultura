<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.user.nickname" var="authNickname"/>
</sec:authorize>
<form role="form" method="post">        
    <input type='hidden' name='board_id' value="${boardVO.board_id}">
    <input type='hidden' name='page' value="${cri.page}">
    <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
    <input type='hidden' name='searchType' value="${cri.searchType}">
    <input type='hidden' name='keyword' value="${cri.keyword}">    
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

<div class="container col-md-10 border2 border-success" style="margin-top: 2%; margin-bottom: 0px;">
    <textarea id='txtarea' readonly="readonly" style="width: 100%; height: 24em; border: 0; background: clear;" >${boardVO.content}</textarea>
</div>

<div class="container col-md-10 " style="padding-bottom: 1%; margin-bottom: 4%; margin-top: 10px;">
    <div class="box-footer">
        <button type="submit" id ="listPage" class="btn btn-success pull-right">목록으로</button>
        <sec:authorize access="isAuthenticated()">
            <c:if test="${authNickname eq boardVO.member_id }">
                <button type="submit" id ="writeModifyBtn" class="btn btn-success">수정</button>
                <button type="submit" id ="writeAddBtn" class="btn btn-info">삭제</button> 
            </c:if>
        </sec:authorize>
    </div>
</div>

<!-- 댓글 등록 -->
<div class="container col-md-10 card text-dark "
    style="padding: 1%; margin-bottom:2em; line-height: 160%;">
    <sec:authorize access="isAuthenticated()">
        <div class=row>
            <div class="col-md-11">
                <textarea id = "newReplyText" name="replytext" rows="3" placeholder="댓글을 입력해 주세요." class="form-control"></textarea>
            </div>
            <div class="col-md-1" style="padding-top: 38px">                        
                    <input type="hidden" name="replyer" id='newReplyWriter' value="${authNickname }">
                    <input id="replyAddBtn" class="btn btn-success" Type='Submit' Value='등록'></input>         
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
<div id="replies" class="container">    

</div>
<script id="template" type="text/x-handlebars-template">
{{#each .}}    
    <div class="card" id="{{reply_id}}">
        <div class="card-body">
            <div class="row" >                
                <div class="col-md-12">
                   <p>
                       <strong>{{replyer}}</strong>  
                   </p>
                   <div class="clearfix"></div>
                   <pre class=pretag>{{replytext}}</pre>
                   {{#eqReplyer replyer}}
                       <button type="submit" id="replyDelBtn" class="btn btn-info float-right ml-1 ">삭제</button>                        
                       <button type="submit" id="replyModBtn" class="btn btn-success float-right ">수정</button>
                   {{/eqReplyer}}
                </div>
            </div>
        </div>
    </div>
{{/each}}
</script> 

<script>
    Handlebars.registerHelper("eqReplyer", function(replyer, block){
    	var accum = '';
    	if(replyer == "${authNickname}"){
    		accum += block.fn();
    	}
    	return accum;
    });
    
    var board_id = ${boardVO.board_id }; //게시글 번호    
    //댓글 정보 가져오기
    function getAllList(){              
       $.getJSON("/replies/all/"+ board_id, function(data){
           var source = $('#template').html();
           var template = Handlebars.compile(source);
           $('#replies').html(template(data));
       });
    }

    //댓글 수정 버튼
    var replyId = '';
    var replyContent ='';    
    var textareaTag = '<textarea class = "replyTextarea form-control border-success"></textarea>';
    
    //댓글 수정 버튼 클릭시 UI 조정
    $("#replies").on("click", '#replyModBtn', function(){
    	//1. 기존의 글을 보여주는 태그를 없앤다.
    	replyModify = $(this).prev().prev();
    	replyContent = replyModify.text();    	
    	replyModify.remove();
    	//2. 택스트가 입력 가능하도록 만든다.
    	$(this).prev().before(textareaTag);    	
        $(this).prev().prev().val(replyContent);
        //3. 수정 혹은 수정취소 버튼을 만든다.
    	$(this).attr("id", "replyModAdd");
    	$(this).prev().attr("id", "replyModCancelBtn");
    	$(this).prev().text("취소");    	
    });
    
    //댓글 수정 도중 취소
    var preTag = "<pre class=pretag></pre>";
    $("#replies").on("click", '#replyModCancelBtn', function(){
    	$(this).prev().remove();
    	$(this).before(preTag);
        $(this).prev().text(replyContent);
        $(this).attr("id", "replyDelBtn");
        $(this).text("삭제");
        $(this).next().attr("id", "replyModBtn");        
    });
    
    //댓글 작성
    $("#replyAddBtn").on("click", function() {        
     	if(!$("#newReplyText").val()){
            alert("내용을 입력해 주세요.");
            return;
        }
     	//로그인시에 댓글 처리 되도록 해야할 듯
        var replyer = $("#newReplyWriter").val();
        var replytext = $("#newReplyText").val();
        
        $.ajax({
            type : 'post',
            url : '/replies',
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',
            data : JSON.stringify({
                board_id : board_id,
                replyer : replyer,
                replytext : replytext
            }),
            success : function(result) {
    
                if (result == 'SUCCESS') {    
                    alert("등록 되었습니다.");                    
                    getAllList();
                    $("#newReplyText").val("");
                }
            }
        });
    });
    
    //댓글 수정
    $("#replies").on("click", '#replyModAdd', function(){    	
        var replyId = $(this).parent().parent().parent().parent().attr("id");        
        var replytext = $(this).prev().prev().val();        
        
        $.ajax({
              type:'put',
              url:'/replies/'+replyId,
              headers: { 
                    "Content-Type": "application/json",
                    "X-HTTP-Method-Override": "PUT" },
              data:JSON.stringify({replytext:replytext}), 
              dataType:'text', 
              success:function(result){
                  console.log("result: " + result);
                  if(result == 'SUCCESS'){                	  
                      alert("수정 되었습니다.");
                      //getPageList(replyPage);
                  }
          }});
          //댓글 수정 뒤의 UI처리
          $(this).prev().prev().remove();
          $(this).prev().before(preTag);
          $(this).prev().prev().text(replytext);
          $(this).prev().attr("id", "replyDelBtn");
          $(this).prev().text("삭제");
          $(this).attr("id", "replyModBtn"); 
      });

    // 댓글 삭제
    $("#replies").on("click", '#replyDelBtn', function(){
    	var replyId = $(this).parent().parent().parent().parent().attr("id");
    	$.ajax({
            type : 'delete',
            url : '/replies/' + replyId,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "DELETE"
            },
            dataType : 'text',
            success : function(result) {
                console.log("result: " + result);
                if (result == 'SUCCESS') {
                    alert("삭제 되었습니다.");
                    $("#"+replyId).remove();                    
                }
            }
        });
    });

    var formObj = $("form[role='form']");
    console.log(formObj);
    //게시글 수정
    $("#writeModifyBtn").on("click", function() {
        formObj.attr("action", "/board/modify");
        formObj.attr("method", "get");
        formObj.submit();
    });
    //게시글 삭제
    $("#writeAddBtn").on("click", function() {
        formObj.attr("action", "/board/remove");
        formObj.submit();
    });
    //목록으로 돌아가기
    $("#listPage").on("click", function() {
        formObj.attr("method", "get");
        formObj.attr("action", "/home");
        formObj.submit();
    });
    
    $(document).ready(function() {
    	getAllList();    	
    });
</script>

<%@ include file="../template/footer.jsp"%>