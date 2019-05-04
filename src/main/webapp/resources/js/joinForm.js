	function Reset() {
		document.getElementById("userId").value = "";
		document.getElementById("userPw").value = "";
		document.getElementById("passwdConfirm").value = "";
		document.getElementById("nickname").value = "";
		document.getElementById("email").value = "";
		document.getElementById("blog").value = "";
	}

	function Check_id() {
		browsing_window = window
				.open(
						"LogIn_Check_Id.jsp?member_id=" + join.member_id.value,
						"_idcheck",
						"height=200,width=300, menubar=no,directories=no,resizable=no,status=no,scrollbars=yes");
		browsing_window.focus();
	}
	
	$(document).ready(function(){
		//아이디 중복체크
		$("#checkIdBtn").on("click", function() {    
            if(!$("#userId").val()){
                alert("내용을 입력해 주세요.");
                return;
            }                
            var userId = $("#userId").val();  
        
            $.ajax({
                type : 'post',
                url : '/user/checkId/'+userId,
                headers : {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType : 'text',             
                success : function(object) {                	
                	var result = JSON.parse(object);                	
                    if (result == 1)
                        alert("이미 등록된 아이디입니다.");                     
                    else{
                        alert("사용가능한 아이디입니다.");
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
            var nickname = $("#nickname").val();  
        
            $.ajax({
                type : 'post',
                url : '/user/checkNickname/'+nickname,
                headers : {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType : 'text',             
                success : function(object) {                	
                	var result = JSON.parse(object);
                    if (result == 1)
                        alert("이미 사용중인 닉네임입니다.");                     
                    else
                        alert("사용 가능한 닉네임입니다.");                
                }
            });
        });		
	});
	