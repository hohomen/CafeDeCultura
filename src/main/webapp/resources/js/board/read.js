const boardId = $("input[name='boardId']").val();
const replyer = $("input[name='userId']").val();
let status = false; // 대댓글 혹은 수정을 동시에 할 수 없도록
let reParent = "";  // 부모 댓글

const formObj = $("form[role='form']");    

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

//수정 권한이 없는 답장
const noneAuthReplyTemplate = (comments) => {
    let str = 
        `<div class="card" id="${comments.replyId}" style="padding: 2%;">
             <div class="row depth${comments.reDepth}">
                 <div class="iamgeBox">
                     <img src="/file/displayFile?fileName=${comments.image}&pathType=userProfile" class="img img-rounded img-fluid" />
                 </div>
                 <div class="contentBox" style="padding-bottom: 2px;">
                     <strong>${comments.nickname}</strong>&nbsp;
                     <small>${comments.regDate}</small>&nbsp;                     
                     <span id="${comments.reParent}"></span>
                     <span id="addReReply">답글</span>
                     <p>${comments.replyText}</p>
                 </div>
             </div>            
         </div>` 
    return str;
}

//수정 권한이 있는 답장 
const authReplyTemplate = (comments) => {
    let str = 
        `<div class="card" id="${comments.replyId}" style="padding: 2%;">
             <div class="row depth${comments.reDepth}">
                 <div class="iamgeBox">
                     <img src="/file/displayFile?fileName=${comments.image}&pathType=userProfile" class="img img-rounded img-fluid" />
                 </div>
                 <div class="contentBox" style="padding-bottom: 2px;">
                     <strong>${comments.nickname}</strong>&nbsp;
                     <small>${comments.regDate}</small>&nbsp;
                     <span id="replyModify">수정</span>&nbsp;
                     <span id="replyDelete">삭제</span>
                     <p>${comments.replyText}</p>
                 </div>
             </div>            
         </div>` 
    return str;
}
    
const replyAddTemplate = () => {
    const str =
        `<hr class="onReply">`+
        `<div class="row onReply" style="padding:1%">`+
        `    <textarea rows="3" id="reReplyTextarea"></textarea>`+
        `    <button type="button" id="reReplyButton" class="btn btn-secondary">등록</button>`+
        `</div>`;
    return str;
}

const replyModTemplate = (contents) => {
    const str =
        `<hr class="onReply">`+
        `<div class="row onReply" style="padding:1%">`+
        `    <textarea rows="3" id="reReplyTextarea">${contents}</textarea>`+
        `    <button type="button" id="ReplyModButton" class="btn btn-secondary">수정</button>`+
        `</div>`;
    return str;
}

function getReplyList(){
    $.getJSON("/replies/all/"+ boardId, function(data){        
        let str=""; 
        
        $(data).each((index,item)=>{
            if(item.replyer == replyer)
                str += authReplyTemplate(item);
            else
                str += noneAuthReplyTemplate(item);
        })          
        $('#replies').html(str);
    })
}

//일반 댓글 작성
$("#replyAddBtn").on("click", function() {        
   if(!$("#newReplyText").val()){
        alert("내용을 입력해 주세요.");
        return;
    }              
    let replyText = $("#newReplyText").val();        
    $.ajax({
        type : 'post',
        url : '/replies',
        headers : {
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "POST"
        },
        dataType : 'text',
        data : JSON.stringify({
            boardId : boardId,
            replyer : replyer,
            replyText : replyText
        }),
        success : function(result) {    
            if (result == 'SUCCESS') {
                getReplyList();                    
                $("#newReplyText").val("");
            }
        }
    });
});
 
//대댓글 버튼 클릭시
$("#replies").on("click", "#addReReply", function(){
     if(replyer == ""){
         alert("로그인 후 이용해 주세요!");
         return;
     }
     if(status){
         $("#reReplyAddCancel").text("답글").attr("id", "addRereply");
         $(".onReply").remove();
     }else{
         status = true;
     }             
     $(this).parent().parent().after(replyAddTemplate());
     reParent = $(this).prev().attr("id");
     $(this).text("답글 취소").css("color", "red").attr("id", "reReplyAddCancel");        
 });
 
 //대댓글 취소
 $("#replies").on("click", "#reReplyAddCancel", function(){        
     $(".onReply").remove();
     $(this).text("답글").css("color", "black").attr("id", "addReReply");// 수정 취소시에는 달라야 한다.
     status = false;
     reParent = "";
 }); 
  
 //대댓글 등록
 $("#replies").on("click", "#reReplyButton", function(){        
     if(!$("#reReplyTextarea").val()){
         alert("내용을 입력해 주세요.");
         return;
     }                        
     let replyText = $("#reReplyTextarea").val();
     $.ajax({
         type : 'post',
         url : '/replies',
         headers : {
             "Content-Type" : "application/json",
             "X-HTTP-Method-Override" : "POST"
         },
         dataType : 'text',
         data : JSON.stringify({
             boardId : boardId,
             replyer : replyer,
             replyText : replyText,
             reParent : reParent
         }),
         success : function(result) {
 
             if (result == 'SUCCESS') {    
                 getReplyList();
             }
         }
     });        
 });
 
 //댓글 수정 버튼 클릭 시
 $("#replies").on("click", "#replyModify", function(){
     if(status){
         $("#replyModCancel").text("수정").attr("id", "replyModify"); 
         // 수정인지 답글인지 판별이 필요 --> flag를 1이면 답글 2이면 수정으로 하면 된다. --> 텍스트로 ㄱ
         $(".onReply").remove();
     }else{
         status = true;
     }
     $(this).text("수정 취소").css("color", "red").attr("id", "replyModCancel");
     content = $(this).next().next().text();     
     $(this).parent().parent().after(replyModTemplate(content));                
 }); 

 //댓글 수정 취소
 $("#replies").on("click", "#replyModCancel", function(){        
     $(".onReply").remove();
     $(this).text("수정").css("color", "black").attr("id", "replyModify");// 수정 취소시에는 달라야 한다.
     status = false;
     reParent = "";
 });
 
 //댓글 수정
 $("#replies").on("click", '#ReplyModButton', function(){
     var replyId = $(this).parent().parent().attr("id");
     var replyText = $(this).prev().val();
     alert(replyId);
     alert(replyText);     
     $.ajax({
           type:'put',
           url:'/replies/'+replyId,
           headers: { 
                 "Content-Type": "application/json",
                 "X-HTTP-Method-Override": "PUT" },
           data:JSON.stringify({               
               replyText:replyText
           }), 
           dataType:'text', 
           success:function(result){
               console.log("result: " + result);
               if(result == 'SUCCESS'){
                   alert("수정 되었습니다.");
               }
           }
       });
   });

 // 댓글 삭제
 $("#replies").on("click", '#replyDelete', function(){
 	var replyId = $(this).parent().parent().parent().parent().attr("id");
 	alert(replyId);
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
 
//summernote 에디터 내부 이미지 크기 조정
 function adjustImage(){
    $('img').addClass('img');
    $('img').addClass('img-rounded');
    $('img').addClass('img-fluid');
 }
 
 $(document).ready(function() {    	
 	adjustImage(); 	
 });    
 