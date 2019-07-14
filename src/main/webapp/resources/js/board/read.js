const boardId = $("input[name='boardId']").val();
const userId = $("input[name='userId']").val();
let reParent = "";  // 부모 댓글
let On_Writing_Status = false; // 대댓글 혹은 수정을 동시에 할 수 없도록
let Writing_Purpose = "";
let like_status = false;


function isLoggedInId(){
    if(userId == ""){
        if(confirm("로그인 하시겠습니까?")){
            location.href="/user/loginForm";
        }
        return true;
    }
}

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

//좋아요 버튼
$("#likeBtn").on("click", function(){    
    if(isLoggedInId()) return
    
    if(like_status){
        removeLike();
    }else{
        registerLike();
    }
});

function removeLike(){
    $.ajax({
        type:'delete',
        url:'/like/board/'+boardId+'/userId/'+userId,
        headers: { 
              "Content-Type": "application/json",
              "X-HTTP-Method-Override": "DELETE" 
        },
        dataType:'text',
        success:function(result){
            console.log("result: " + result);
            if(result == 'SUCCESS'){                
                $('#likeBtn').removeClass("text-white").removeClass("btn-danger").addClass("btn-outline-danger");
                like_status = false;
            }
        }
    });
}

function registerLike(){
    $.ajax({
        type:'post',
        url:'/like/board/'+boardId+'/userId/'+userId,
        dataType:'text',
        success:function(result){
            console.log("result: " + result);
            if(result == 'SUCCESS'){
                $('#likeBtn').removeClass("btn-outline-danger").addClass("btn-danger").addClass("text-white");
                like_status = true;
            }
        }
    });
}

//수정 권한이 없는 댓글 리스트
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

//수정 권한이 있는 댓글 리스트
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

const deletedReplyTemplate = (comments) => {
    let str = 
        `<div class="card" id="${comments.replyId}" >       
             <div class="row depth${comments.reDepth}">                    
                 <div class="contentBox">
                     <pre>${comments.replyText}</pre>
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
    $.getJSON("/replies/all/"+boardId, function(data){
        let str="";
        
        $(data).each((index,item)=>{
            if(item.replyer == userId)
                str += authReplyTemplate(item);
            else if(item.replyer == "none"){
                str += deletedReplyTemplate(item);
            }                
            else
                str += noneAuthReplyTemplate(item);
        })
        $('#replies').html(str);
    })
}

$("#needToLogin").on("click", function(){
    isLoggedInId(); 
});

//기본 댓글 작성
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
            replyer : userId,
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
     if(isLoggedInId()) return;
     if(On_Writing_Status){
         if(Writing_Purpose == "답글"){
             $("#reReplyAddCancel").text("답글").css("color", "black").attr("id", "addReReply");
         }else{
             $("#replyModCancel").text("수정").css("color", "black").attr("id", "replyModify");
         }
         $(".onReply").remove();
     }
     
     On_Writing_Status = true;
     Writing_Purpose = "답글";
     
     $(this).parent().parent().after(replyAddTemplate());
     reParent = $(this).prev().attr("id");
     $(this).text("답글 취소").css("color", "red").attr("id", "reReplyAddCancel");
 });
 
 //대댓글 취소
 $("#replies").on("click", "#reReplyAddCancel", function(){
     $(".onReply").remove();
     $(this).text("답글").css("color", "black").attr("id", "addReReply");
     On_Writing_Status = false;
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
             replyer : userId,
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
     if(On_Writing_Status){
         if(Writing_Purpose == "수정"){
             $("#replyModCancel").text("수정").css("color", "black").attr("id", "replyModify");
         }else{
             $("#reReplyAddCancel").text("답글").css("color", "black").attr("id", "addReReply");
         }
         $(".onReply").remove();
     }
     
     On_Writing_Status = true;
     Writing_Purpose = "수정";     
     
     $(this).text("수정 취소").css("color", "red").attr("id", "replyModCancel");
     content = $(this).next().next().text();     
     $(this).parent().parent().after(replyModTemplate(content));                
 }); 

 //댓글 수정 취소
 $("#replies").on("click", "#replyModCancel", function(){        
     $(".onReply").remove();
     $(this).text("수정").css("color", "black").attr("id", "replyModify");// 수정 취소시에는 달라야 한다.
     On_Writing_Status = false;
     reParent = "";
 });
 
 //댓글 수정
 $("#replies").on("click", '#ReplyModButton', function(){
     var replyId = $(this).parent().parent().attr("id");
     var replyText = $(this).prev().val();          
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
                   getReplyList();
               }
           }
       });
   });

 // 댓글 삭제
 $("#replies").on("click", '#replyDelete', function(){
    let replyId = $(this).parent().parent().parent().attr("id");
 	let replyer = "none";
 	let replyText = "  삭제된 댓글입니다.";
 	$.ajax({
         type : 'delete',
         url : '/replies/'+ replyId ,
         headers : {
             "Content-Type" : "application/json",
             "X-HTTP-Method-Override" : "DELETE"
         },
         data:JSON.stringify({             
             replyer : replyer,
             replyText : replyText
         }),
         dataType : 'text',
         success : function(result) {
             console.log("result: " + result);
             if (result == 'SUCCESS') {
                 getReplyList();                   
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
 
 function isLikedBoard(){
     if(userId == "")  return
     $.get("/like/board/"+ boardId+"/userId/"+userId, function(result){        
         if(result == 'Liked_Board'){            
             $('#likeBtn').removeClass("btn-outline-danger").addClass("btn-danger").addClass("text-white");
             like_status = true;
         }
     })
 }
 
 $(document).ready(function() {
 	adjustImage();
 	isLikedBoard();
 });    
 