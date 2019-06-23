    const userId = $('#userId').val();

    //이미지 파일 등록 여부 확인
    function hasProfileImage(){
        var authImage = $('#image').val();
        if(authImage=="/defaultProfile.jpg"){
            return;
        }else{
            var str = "<img src='/file/displayFile?fileName="+authImage
                    +"&pathType=userProfile' class='img img-rounded img-fluid'/>"
                    + "<small id='deleteImage'data-src="+authImage+">X</small>";            
            $(".fileDrop").hide();
            $(".uploadedList").append(str);
        }
    }
    
    //이미지 파일 삭제
    $(".uploadedList").on("click", "small", function(event){             
        $.ajax({
            url:"/file/deleteFile",
            type : "post",
            data : {fileName : $(this).attr("data-src"),
                    pathType : "profileImage",
                    userId : userId
            },
            dataType:"text",
            success:function(result){
                if(result == 'deleted'){
                    location.reload();
                }
            }
        });
    });
    
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
                  var str = "<img src='/file/displayFile?fileName="+data+"&pathType=userProfile' class='img img-rounded img-fluid'/>"
                            + "<small id='deleteImage'data-src="+data+">X</small>";
                  $("#image").val(data);
                  $(".fileDrop").hide();
                  $(".uploadedList").append(str);
              }
        });
    });    

    function checkImageType(fileName){    
        const pattern = /jpg|gif|png|jpeg/i;    
        return fileName.match(pattern);        
    }
    
    //회원 정보 수정
    const originNickname = $("#originNickname").val();    
    let status = "none"; // 닉네임 중복확인 여부    
    $(".btn-info").on("click", function() {        
        let nickname = $('#nickname').val();
        if(originNickname != nickname){
            if(status == "none"){
                alert("닉네임 중복 여부를 확인해 주세요.");
                return;
            }                
        }
        let email = $('#email').val();
        let blog = $('#blog').val();
        let birth = $('#birth').val();
        let address = $('#address').val();
        let image = $('#image').val();
        $.ajax({
            type : 'put',
            url : '/user/modify/' + userId,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "PUT"
            },
            data : JSON.stringify({
                userId : userId,
                nickname : nickname,
                email : email,
                blog : blog,
                birth : birth,
                address : address,
                image : image
            }),
            dataType : 'text',
            success : function(result) {
                console.log("result: " + result);
                if (result == 'SUCCESS') {
                    location.reload();
                    alert("수정 되었습니다.");
                }
            }
        });
    });
    
  //닉네임 중복체크
    $("#checkNicknameBtn").on("click", function() {    
        if(!$("#nickname").val()){
            alert("내용을 입력해 주세요.");
            return;
        }                
        let nickname = $("#nickname").val();  
    
        $.ajax({
            type : 'post',
            url : '/user/checkNickname/'+nickname,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',             
            success : function(object) {                    
                let result = JSON.parse(object);
                if (result == 1)
                    alert("이미 사용중인 닉네임입니다.");                     
                else
                    alert("사용 가능한 닉네임입니다.");
                    status = "check";
            }
        });
    });
    
    //다시 쓰기
    $(".btn-danger").on("click", function(){
        location.reload();
    });
    
    $(document).ready(function() {
        hasProfileImage();
    });
