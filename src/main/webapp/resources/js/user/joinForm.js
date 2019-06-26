    
    //아이디 중복체크
    $("#checkIdBtn").on("click", function() {
        const btn = $(this);
        if(!$("#userId").val()){
            alert("내용을 입력해 주세요.");
            return;
        }                
        let userId = $("#userId").val();
        
        isUsableId(userId).then((result) =>{            
            if(result === "Used_ID"){
                alert("이미 사용중인 아이디 입니다.");            
            }else{
                alert("사용 가능한 아이디입니다.");
            }
        })
    });
    
    const isUsableId = (inputId) =>{
        return new Promise( (resolve, reject) =>{
            $.ajax({
                type : 'post',
                url : '/user/checkId/'+inputId,
                async : false,
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
    
    //닉네임 중복체크
    $("#checkNicknameBtn").on("click", function() {
        const btn = $(this);
        if(!$("#nickname").val()){
            alert("내용을 입력해 주세요.");
            return;
        }
        
        let nickname = $("#nickname").val();
        
        isUsableNickname(nickname).then((result) =>{            
            if(result == 1){
                alert("이미 사용중인 닉네임입니다.");
            }else{
                alert("사용 가능한 닉네임입니다.");
            }            
        }).catch((error) => {
            errorAlert('확인에 실패했습니다');
        });
    });
    
    const isUsableNickname = (nickname) =>{
        return new Promise( (resolve, reject) =>{
            $.ajax({
                type : 'post',
                url : '/user/checkNickname/'+nickname,
                async : false,
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
    
    //회원 가입       
    $(".btn-info").on("click", function(){        
        let userId = $("#userId").val();
        let nickname = $("#nickname").val();
        
        if(isUsableId(userId) === "Used_ID"){
            alert("이미 사용중인 아이디 입니다.")
        }        
        
        isUsableId(userId).then((result) =>{
            if(result === "Used_ID"){
                alert("이미 사용중인 아이디 입니다.");
                return
            }else{
                isUsableNickname(nickname).then((result) =>{            
                    if(result === "Used_Nick"){
                        alert("이미 사용중인 닉네임입니다.");
                        return
                    }else{
                        if(!joinFrom.checkValidity()){
                            joinFrom.find(':submit').click();
                        }else{
                            joinFrom.submit();
                        }                            
                    }            
                })
            }
        })
    });
    
    //이미지 처리
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