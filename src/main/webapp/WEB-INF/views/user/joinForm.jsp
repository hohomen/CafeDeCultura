<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<style>

</style>

<div class='container'>
	<div class="row jumbotron text-dark">
		<h2>
			<strong>회원 가입</strong>
		</h2>
	</div>
	<FORM Name='join' Method='post' Action='/user/register' style="margin-left: 1%">
	<div class="row">
	
	   <div class="card col-md-6">
	   <div class="card-body">
	   	   
	       <h4>필수 정보</h4>
	       <hr color='#EAEAEA' style="margin-bottom: 2em;">
	                   
        <div class="row">
            <strong><label for="userId" class="text-dark">&nbsp;ID </label></strong> 
        </div>
        <div class="row">
            <div class="form-group form-check-inline">
                <input type="text" id="userId" name="userId" required class="form-control">&nbsp; 
                <input type="button" id="checkIdBtn" value="중복 검사" class="btn btn-coffee">
                <input type='hidden' name='idCheck' value=''>
            </div>
        </div>
        <div class="row">
            <strong><label for="userPw" class="text-dark">&nbsp;비밀번호</label></strong>
        </div>
        <div class="row">
            <div class="form-group ">
                <input type="password" id="userPw" name="userPw" required class="form-control">
            </div>
        </div>
        <div class="row">
            
                <strong><label for="passwdConfirm" class="text-dark">&nbsp;비밀번호 확인</label></strong>
            
        </div>
        <div class="row">
            <div class="form-group ">            
                <input type="password" id="passwdConfirm" name="passwdConfirm" required 
                       class="form-control">
            </div>
        </div>
        <div class="row">
            <strong><label for="nickname" class="text-dark">&nbsp;닉네임</label></strong>
        </div>
        <div class="row">
            <div class="form-group form-check-inline">
                <input type="text" id="nickname" name="nickname" required class="form-control">&nbsp;
                <input type="button" id="checkNicknameBtn" value="중복 검사" class="btn btn-coffee" >
                <input type='hidden' name='nicknameCheck' value=''>
            </div>
        </div>
        <div class="row">            
                <strong><label for="email" class="text-dark">&nbsp;이메일</label></strong>            
        </div>
        <div class="row">                     
                <input type="text" id="email" name="email" required placeholder="ex) donystack@gmail.com" class="form-control">                 
        </div>
        
        <hr color='#EAEAEA' style="margin-bottom: 4%; margin-top: 4%;">
        <div class="row">
            <div class="form-group col-md-6">
                <input type='submit' value='회원가입' class="btn btn-info" > &nbsp;&nbsp; 
                <input type='button' value='다시 쓰기' class="btn btn-danger" OnClick='Reset()'>
            </div>
        </div>
        </div>
	   </div>
	   
	   
	   <div class="card col-md-6 ">
	   <div class="card-body ">
           <h4>선택 정보</h4>
           <hr color='#EAEAEA' style="margin-bottom: 2em;">            
        <div class="row">
            <strong><label for="blog" class="text-dark">&nbsp;블로그</label></strong>
        </div>
        <div class="row" style="margin-bottom: 2%";>           
                <input type="text" id="blog" name="blog" placeholder="ex) https://cafeDeCultura.github.io" class="form-control">            
        </div>
        <div class="row">
            <strong><label for="birth" class="text-dark">&nbsp;생일</label></strong>
        </div>
        <div class="row" style="margin-bottom: 2%";>
            <div class='form-group'>           
                <input type="date" id="birth" name="birth" class="form-control">
            </div>
        </div>        
        <div class="row">
            <strong><label for="address" class="text-dark">&nbsp;활동 지역</label></strong>
        </div>
        <div class="row" style="margin-bottom: 2%";>           
                <input type="text" id="address" name="address" class="form-control">            
        </div>
        <div class="row">
            <strong><label for="blog" class="text-dark">&nbsp;프로필 사진</label></strong>
        </div>
        <div class="fileDrop">
        <div class="row " style="display: flex; justify-content: center;">            
                <img src="/resources/image/defaultProfile.jpg" class="img img-rounded img-fluid">
        </div><br>
        <p style="text-align:center;">프로필 사진을 드래그하여 업로드하세요!</p>
        </div>        
        <div class="uploadedList row" style="display: flex; justify-content: center;">
        </div>       
        <input type="hidden" id="inputTagImage" name="image">
        
        </div>
       </div>
    
	</div>	
</FORM>
        


	
	<br>
</div>

<script src="/resources/js/joinForm.js"></script>
<script>
        $(".fileDrop").on("dragenter dragover", function(event) {
            event.preventDefault();
        }); 

        $(".fileDrop").on("drop", function(event){
            event.preventDefault();
            var files = event.originalEvent.dataTransfer.files;
            var file = files[0]; 
            if(!checkImageType(file.name)){
            	alert("이미지 파일이 아닙니다.");
            	return;
            }
            var formData = new FormData();
            formData.append("file", file);
            $.ajax({
                  url: '/file/uploadAjax',
                  data: formData,
                  dataType:'text',
                  processData: false,
                  contentType: false,
                  type: 'POST',
                  success: function(data){
                	  var srcc = '/file/displayFile?fileName='+data+'&pathType=userProfile';
                      var str = "<a href='/file/displayFile?fileName="+getImageLink(data)+"&pathType=userProfile'>"
                                  +"<img src='/file/displayFile?fileName="+data+"&pathType=userProfile' class='img img-rounded img-fluid'/>"
                                  +"</a><small id='deleteImage'data-src="+data+">X</small>";
                      $("#inputTagImage").val(data);
                      $(".fileDrop").hide();
                      $(".uploadedList").append(str);
                  }
            });
        });

        $(".uploadedList").on("click", "small", function(event){
               var that = $(this);
               $.ajax({
                   url:"/file/deleteFile",
                   type : "post",
                   data : {fileName : $(this).attr("data-src"),
                	       pathType : "profileImage"
                   },
                   dataType:"text",
                   success:function(result){
                       if(result == 'deleted'){
                    	   $("#inputTagImage").val('');
                           that.parent().empty();
                           $(".fileDrop").show();
                       }
                   }
               });
        });
        
        //UUID와 같은 파일 이름을 제거
        function getOriginalName(fileName){
            if(checkImageType(fileName)){
                return;
            }    
            var idx = fileName.indexOf("_") + 1 ;
            return fileName.substr(idx);    
        }

        function checkImageType(fileName){    
            var pattern = /jpg|gif|png|jpeg/i;    
            return fileName.match(pattern);        
        }
        
        //썸네일 이미지가 아닌 원본 이미지를 추출하기 위해서
        function getImageLink(fileName){
            if(!checkImageType(fileName)){
                return;
            }
            var front = fileName.substr(0,12);
            var end = fileName.substr(14);    
            return front + end;    
        }
         
        
/*      
$(".fileDrop").on("drop", function(event) {
    event.preventDefault();
    
    var files = event.originalEvent.dataTransfer.files;
    
    var file = files[0];

    //console.log(file);
    var formData = new FormData();
    
    formData.append("file", file);

    
    $.ajax({
          url: '/uploadAjax',
          data: formData,
          dataType:'text',
          processData: false,
          contentType: false,
          type: 'POST',
          success: function(data){
              
              var str ="";
              
              console.log(data);
              console.log(checkImageType(data));
              
              if(checkImageType(data)){
                  str ="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
                          +"<img src='displayFile?fileName="+data+"'/></a>"
                          +data +"</div>";
              }else{
                  str = "<div><a href='displayFile?fileName="+data+"'>" 
                          + getOriginalName(data)+"</a></div>";
              }
              
              $(".uploadedList").append(str);
          }
        });         
});  */

/*  $(".fileDrop").on("drop", function(event) {
            event.preventDefault();
            
            var files = event.originalEvent.dataTransfer.files;
            
            var file = files[0];

            //console.log(file);
            var formData = new FormData();
            
            formData.append("file", file);
            
            $.ajax({
                  url: '/file/uploadAjax',
                  data: formData,
                  dataType:'text',
                  processData: false,
                  contentType: false,
                  type: 'POST',
                  success: function(data){
                      
                      var str ="";
                      
                      console.log(data);
                      console.log(checkImageType(data));
                      
                      if(checkImageType(data)){
                          str ="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
                                  +"<img src='displayFile?fileName="+data+"'/></a>"
                                  +data +"</div>";
                      }else{
                          str = "<div><a href='displayFile?fileName="+data+"'>" 
                                  + getOriginalName(data)+"</a></div>";
                      }
                      
                      $(".uploadedList").append(str);
                     
                  }
                });
            
        });  */
            
</script>

<%@ include file="../template/footer.jsp"%>