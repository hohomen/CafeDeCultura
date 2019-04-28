<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>

<div class='container card'>
	<div class="row jumbotron text-dark">
		<h2>
			<strong>회원 가입</strong>
		</h2>
	</div>
	<FORM Name='join' Method='post' Action='/user/register' style="margin-left: 3%">	    
		<div class="row">
			<strong><label for="userId" class="text-dark">&nbsp;ID </label></strong> </div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="text" id="userId" name="userId" required class="form-control">&nbsp; 
				<input type="button" id="checkIdBtn" value="중복 검사" class="btn btn-warning">
			    <input type='hidden' name='idCheck'	value=''>
			</div>
		</div>
		<div class="row">
			<strong><label for="userPw" class="text-dark">&nbsp;비밀번호</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="password" id="userPw" name="userPw" required class="form-control">
			</div>
		</div>
		<div class="row">
			<strong><label for="passwdConfirm" class="text-dark">&nbsp;비밀번호 확인</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="password" id="passwdConfirm" name="passwdConfirm" required 
				       class="form-control">
			</div>
		</div>
		<div class="row">
			<strong><label for="nickname" class="text-dark">&nbsp;닉네임</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="text" id="nickname" name="nickname" required class="form-control">&nbsp;
				<input type="button" id="checkNicknameBtn" value="중복 검사" class="btn btn-warning" >
				<input type='hidden' name='nicknameCheck' value=''>
			</div>
		</div>
		<div class="row">
            <strong><label for="email" class="text-dark">&nbsp;이메일</label></strong>
        </div>
        <div class="row">
            <div class="form-group col-md-6 form-check-inline">
                <input type="text" id="email" name="email" required placeholder="ex) donystack@gmail.com" class="form-control">
            </div>
        </div>
		<div class="row">
			<strong><label for="blog" class="text-dark">&nbsp;블로그(선택 사항)</label></strong>
		</div>
		<div class="row">
            <div class="form-group col-md-6 form-check-inline">
                <input type="text" id="blog" name="blog" placeholder="ex) https://cafeDeCultura.github.io" class="form-control">
            </div>
        </div>

		<hr color='#EAEAEA' style="margin-bottom: 2%;">

		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type='submit' value='회원가입' class="btn btn-info" > &nbsp;&nbsp; 
				<input type='button' value='다시 쓰기' class="btn btn-danger" OnClick='Reset()'>
			</div>
		</div>
	</FORM>
	<br>
</div>

<script>
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
                    if (result.userId == userId)
                        alert("이미 등록된 아이디입니다.");                     
                    else
                        alert("사용가능한 아이디입니다.");                
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
                    if (result.nickname == nickname)
                        alert("이미 사용중인 닉네임입니다.");                     
                    else
                        alert("사용 가능한 닉네임입니다.");                
                }
            });
        });		
	});
	
</script>
<%@ include file="../template/footer.jsp"%>