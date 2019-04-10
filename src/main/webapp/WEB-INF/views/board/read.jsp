<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<div class="container col-md-10 border2 border-success"
    style="margin-top: 2%; margin-bottom: 0px;">

    <textarea id='txtarea' style="width: 100%; height: 24em; border: 0; background: clear;" readonly="readonly">${boardVO.content}
    </textarea>
</div>

<div class="container col-md-10 "
    style="margin-bottom: 4%; margin-top: 10px;">
    <div class="box-footer">
        <button type="submit" id ="listPage" class="btn btn-success pull-right">목록으로</button>
        <button type="submit" id ="writeModifyBtn" class="btn btn-success">수정</button>
        <button type="submit" id ="writeAddBtn" class="btn btn-info">삭제</button>                  
    </div>
</div>

<!-- 댓글 등록 -->
<div class="container col-md-10 card text-dark "
    style="padding: 1%; margin-bottom:2em; line-height: 160%;">
    <div class=row>
        <div class="col-md-10">
            <textarea id = "newReplyText" name="replytext" rows="3" placeholder="댓글을 입력해 주세요." class="form-control"></textarea>
        </div>
        <div class="col-md-2"><!-- style="padding-top: 38px"  -->
            <input type="text" name="replyer" class="form-control" id='newReplyWriter' placeholder="작성자">
            <input id="replyAddBtn" class="btn btn-success" Type='Submit' Value='등록'></input>
        </div>
    </div>
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
                   <button type="submit" id="replyDelBtn" class="btn btn-info float-right ml-1 ">삭제</button>                        
                   <button type="submit" id="replyModBtn" class="btn btn-success float-right ">수정</button>                  
                </div>
            </div>
        </div>
    </div>
{{/each}}
</script> 

<script>
    //댓글 수정 버튼
    var replyId = '';
    var replyContent ='';    
    var textareaTag = '<textarea class = "replyTextarea form-control border-success"></textarea>';
    
    
    $("#replies").on("click", '#replyModBtn', function(){
    	replyModify = $(this).prev().prev();
    	replyContent = replyModify.text();    	
    	replyModify.remove();
    	
    	$(this).prev().before(textareaTag)    	
        $(this).prev().prev().val(replyContent);
    	$(this).attr("id", "replyModAdd");
    	$(this).prev().attr("id", "replyModCancelBtn");
    	$(this).prev().text("취소");    	
    });       
    
    //댓글 수정 도중 취소
    var preTag = "<pre class=pretag></pre>"
    $("#replies").on("click", '#replyModCancelBtn', function(){
    	$(this).prev().remove();
    	$(this).before(preTag);
        $(this).prev().text(replyContent);
        $(this).attr("id", "replyDelBtn");
        $(this).text("삭제");
        $(this).next().attr("id", "replyModBtn");
        
    });
    

     var board_id = ${boardVO.board_id };
    /* var board_id = 3067 */    
    function getAllList(){              
        $.getJSON("/replies/all/"+ board_id, function(data){            
            var source = $('#template').html();
            var template = Handlebars.compile(source);      
            $('#replies').html(template(data));
        });     
    }
 

    $("#replyAddBtn").on("click", function() {
        
     	if(!$("#newReplyText").val()){
            alert("내용을 입력해 주세요.");
            return;
        }
            
        if(!$("#newReplyWriter").val()){
            alert("작성자를 입력해 주세요.");
            return;
        }    	 
        
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
                    $("#newReplyWriter").val("");
    
                }
            }
        });
    });

    
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
         
          $(this).prev().prev().remove();
          $(this).prev().before(preTag);
          $(this).prev().prev().text(replytext);
          $(this).prev().attr("id", "replyDelBtn");
          $(this).prev().text("삭제");
          $(this).attr("id", "replyModBtn"); 
      });

    
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
                    //$("#replies.replyLi button").remove();
                    $("#"+replyId).remove();
                    
                }
            }
        });
    });

    var formObj = $("form[role='form']");
    console.log(formObj);
    
    $("#writeModifyBtn").on("click", function() {
        formObj.attr("action", "/board/modify");
        formObj.attr("method", "get");
        formObj.submit();
    }); 
    $("#writeAddBtn").on("click", function() {
        formObj.attr("action", "/board/remove");
        formObj.submit();
    });
    $("#listPage").on("click", function() {
        formObj.attr("method", "get");
        formObj.attr("action", "/home");
        formObj.submit();
    });

    /*수정, 삭제 페이지 이동  */
    $(document).ready(function() {      	
    	getAllList();
    	checkReply();
    });
</script>

<%@ include file="../template/footer.jsp"%>