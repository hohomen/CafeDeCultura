
    //댓글 정보 가져오기
    function getAllList(){             
       $.getJSON("/replies/all/"+ G_BOARD_ID, function(data){
           var source = $('#template').html();
           var template = Handlebars.compile(source);
           $('#replies').html(template(data));
       });
    }

    //댓글 수정 버튼 클릭시 UI 조정
    var replyId = '';
    var replyContent ='';    
    var textareaTag = '<textarea class = "replyTextarea form-control border-success"></textarea>';
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
                boardId : G_BOARD_ID,
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