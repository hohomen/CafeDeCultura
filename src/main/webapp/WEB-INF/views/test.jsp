<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
<title>Insert title here</title>
<style>
#modDiv {
    width: 300px;
    height: 100px;
    background-color: gray;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -50px;
    margin-left: -150px;
    padding: 10px;
    z-index: 1000;
}

.pagination {
  width: 100%;
}

.pagination li{
  list-style: none;
  float: left; 
  padding: 3px; 
  border: 1px solid blue;
  margin:3px;  
}

.pagination li a{
  margin: 3px;
  text-decoration: none;  
}

</style>
</head>
<body>

    <div id='modDiv' style="display: none;">
        <div class='modal-title'></div>
        <div>
            <input type='textarea' id='replytext'>
        </div>
        <div>
            <button type="button" id="replyModBtn">Modify</button>
            <button type="button" id="replyDelBtn">DELETE</button>
            <button type="button" id='closeBtn'>Close</button>
        </div>
    </div>

<h2> Ajax Test Page</h2>

<div>
    <div>
        REPLYER <input type='text' name='replyer' id='newReplyWriter'>
    </div>
    <div>
        REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
    </div>
    <button id="replyAddBtn">ADD REPLY</button>
</div>

<ul id="replies">
    
</ul>

<pre id="regularEx"style="word-wrap: break-word;white-space: pre-wrap;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;word-break:break-all;">
<students xmlns="http://www.koreaxml.ac.kr/2015/students">
    <student>
        &ltsid> 100 </sid>
        &ltname> 홍 현 </name>
        <age> 30 </age>
        <profile>
            <html xmlns="http://www.w3.org/1999/xhtml">
                <head>
                    <title> 나의 소개 </title>
                </head>
                <body> 활발한 사회활동 중 </body>
            </html>
        </profile>
    </student>
</students>
</pre>
<script>
    function changeText(){       
    var rText = $("#regularEx").html();
    var aText = rText.replace(/</g, '&lt;');
    $("#regularEx").html(aText);
    alert(rText);
    }
    
    $("#replies").on("click", ".replyLi button", function() {

        var reply = $(this).parent();

        var reply_id = reply.attr("id");
        var replytext = reply.text();

        $(".modal-title").html(reply_id);
        $("#replytext").val(replytext);
        $("#modDiv").show("slow");

    });

    var board_id = 3067;
    
    
    function getAllList(){
    	
    	
    	$.getJSON("/replies/all/"+ board_id, function(data){            
    		var str="";        
            $(data).each(
                    function(){
                        str +="<li id="+this.reply_id+" reply_id='"+this.reply_id+"' class ='replyLi'>"
                        + this.reply_id + ":" + this.replytext
                        + "<button>MOD</button></li>";                  
                    });
            $('#replies').html(str);
        });    	
    }
    
    
    $("#replyAddBtn").on("click", function() {

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

                }
            }
        });
    });
    
    $("#replyDelBtn").on("click", function() {

        var reply_id = $(".modal-title").html(); // text를 하든 html을 하든 어쨌든.
        var replytext = $("#replytext").val();

        $.ajax({
            type : 'delete',
            url : '/replies/' + reply_id,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "DELETE"
            },
            dataType : 'text',
            success : function(result) {
                console.log("result: " + result);
                if (result == 'SUCCESS') {
                    alert("삭제 되었습니다.");
                    $("#modDiv").hide("slow");
                    //$("#replies.replyLi button").remove();
                    $("#"+reply_id).remove();
                    
                }
            }
        });
    });
    
    $("#replyModBtn").on("click",function(){
        
        var reply_id = $(".modal-title").html(); // text를 하든 html을 하든 어쨌든.
        var replytext = $("#replytext").val();
        
        $.ajax({
              type:'put',
              url:'/replies/'+reply_id,
              headers: { 
                    "Content-Type": "application/json",
                    "X-HTTP-Method-Override": "PUT" },
              data:JSON.stringify({replytext:replytext}), 
              dataType:'text', 
              success:function(result){
                  console.log("result: " + result);
                  if(result == 'SUCCESS'){
                      alert("수정 되었습니다.");
                       $("#modDiv").hide("slow");
                       //$("#replies").emply();
                      getAllList();
                      //getPageList(replyPage);
                  }
          }});
      });
    $(document).ready(function(){
    	getAllList(); 
    	$.ajaxSetup({ cache: false });
    	changeText()
    });    
</script>




</body>
</html>