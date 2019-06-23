    
    //아이디 중복체크
    $("#checkIdBtn").on("click", function() {
        const btn = $(this);
        if(!$("#userId").val()){
            alert("내용을 입력해 주세요.");
            return;
        }                
        let userId = $("#userId").val();
        checkDoubleId(userId).then((result) =>{            
            if(result == 1){
                alert("이미 사용중인 아이디 입니다.");            
            }else{
                alert("사용 가능한 아이디입니다..");
            }            
        }).catch((error) => {
            errorAlert('확인에 실패했습니다');
        });    
    });
    
    const checkDoubleId = (inputId) =>{
        return new Promise( (resolve, reject) =>{
            $.ajax({
                type : 'post',
                url : '/user/checkId/'+inputId,
                headers : {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType : 'text',             
                success : (data) => {                    
                    resolve(data);                                
                },
                error : (error) => {
                    reject(error);
                }
            });            
        });
    }
    
    const checkDoubleNickname = (nickname) =>{
        return new Promise( (resolve, reject) =>{
            $.ajax({
                type : 'post',
                url : '/user/checkNickname/'+nickname,
                headers : {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType : 'text',             
                success : (data) => {                    
                    resolve(data);                                
                },
                error : (error) => {
                    reject(error);
                }
            });            
        });
    }
    
    //아이디 사용 취소
    $("button").on("click","#cancelUseOfId", function(){        
        $(this).val("중복 확인").attr("id", "checkIdBtn");
        btn.prev().prop('readonly', false);
    })
    
        
    //닉네임 중복체크
    $("#checkNicknameBtn").on("click", function() {
        const btn = $(this);
        if(!$("#nickname").val()){
            alert("내용을 입력해 주세요.");
            return;
        }
        
        let nickname = $("#nickname").val();
        
        checkDoubleNickname(nickname).then((result) =>{            
            if(result == 1){
                alert("이미 사용중인 닉네임입니다.");            
            }else{
                alert("사용 가능한 닉네임입니다.");
            }            
        }).catch((error) => {
            errorAlert('확인에 실패했습니다');
        });
    });
    
    //닉네임 사용 취소
    $("#cancelUseOfNickname").on("click", function(){
        $(this).val("중복 확인").attr("id", "checkNicknameBtn");
        btn.prev().prop('readonly', false);
    })
    
    //회원 가입       
    $(".btn-info").on("click", function(){
        let userId = $("#userId").val();
        let nickname = $("#nickname").val();
        let flag = false;
        if(userId ==""){
            alert("아이디를 입력해 주세요.");
            return;
        }
        if(nickname ==""){
            alert("닉네임을 입력해 주세요.");
            return;
        }        
        if($('#userPw').val() == ''){
            alert("비밀번호을 입력해 주세요.");
            return;
        }
        if($('#passwdConfirm').val() == ''){
            alert("비밀번호을 입력해 주세요.");
            return;
        }
        if($('#email').val() == ''){
            alert("이메일을 입력해 주세요.");
            return;
        }        
        checkDoubleId(userId).then((result) =>{            
            if(result == 1){
                alert("이미 사용중인 아이디 입니다.비동기 함수 점검중입니다.");
                location.reload();
            }            
        }).catch((error) => {
            errorAlert('회원가입에 실패했습니다');
        });
        checkDoubleNickname(nickname).then((result) =>{            
            if(result == 1){
                alert("이미 사용중인 닉네임 입니다.  + 비동기 함수 점검중입니다.");
                location.reload();
            }   
        }).catch((error) => {
            errorAlert('회원가입에 실패했습니다');
        });        
        joinFrom.submit();
    })    
    
    $(".fileDrop").on("dragenter dragover", function(event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function(event) {
        event.preventDefault();
        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];
        if (!checkImageType(file.name)) {
            alert("이미지 파일이 아닙니다.");
            return;
        }
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url : '/file/uploadAjax',
            data : formData,
            dataType : 'text',
            processData : false,
            contentType : false,
            type : 'POST',
            success : function(data) {                
                let str =
                    "<img src='/file/displayFile?fileName="+data
                        +"&pathType=userProfile' class='img img-rounded img-fluid'/>"
                  + "<small id='deleteImage'data-src="+data+">X</small>";
                $("#inputTagImage").val(data);
                $(".fileDrop").hide();
                $(".uploadedList").append(str);
            }
        });
    });

    $(".uploadedList").on("click", "small", function(event) {
        let that = $(this);
        $.ajax({
            url : "/file/deleteFile",
            type : "post",
            data : {
                fileName : $(this).attr("data-src"),
                pathType : "profileImage",
                userId : "none"
            },
            dataType : "text",
            success : function(result) {
                if (result == 'deleted') {
                    $("#inputTagImage").val('');
                    that.parent().empty();
                    $(".fileDrop").show();
                }
            }
        });
    });

    function checkImageType(fileName) {
        var pattern = /jpg|gif|png|jpeg/i;
        return fileName.match(pattern);
    }    
    
    $(document).ready(function(){
    }); 