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
			<strong><label for="id" class="text-dark">&nbsp;ID </label></strong> </div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input id="checkId" type="text" class="form-control" size="10" name="userId">&nbsp; 
				<input id="checkIdBtn" type="button" class="btn btn-warning " value="중복 검사">
			    <input type='hidden' name='idCheck'	value=''>
			</div>
		</div>
		<div class="row">
			<strong><label for="passwd" class="text-dark">&nbsp;비밀번호</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="password" class="form-control " size="10" name="userPw">
			</div>
		</div>
		<div class="row">
			<strong><label for="passConfirm" class="text-dark">&nbsp;비밀번호	확인</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="password" class="form-control " size="10" name="passwdConfirm">
			</div>
		</div>
		<div class="row">
			<strong><label for="nickname" class="text-dark">&nbsp;닉네임</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input id="checkNickname"type="text" class="form-control " size="10" name="nickname">&nbsp;
				<input id="checkNicknameBtn" type="button" class="btn btn-warning " value="중복 검사">
			</div>
		</div>
		<div class="row">
            <strong><label for="pass" class="text-dark">&nbsp;이메일</label></strong>
        </div>
        <div class="row">
            <div class="form-group col-md-6 form-check-inline">
                <input type="text" class="form-control " placeholder="ex) donystack@gmail.com" size="10" name="email">
            </div>
        </div>
		<div class="row">
			<strong><label for="pass" class="text-dark">&nbsp;블로그(선택 사항)</label></strong>
		</div>
		<div class="row">
            <div class="form-group col-md-6 form-check-inline">
                <input type="text" class="form-control " placeholder="ex) https://cafeDeCultura.github.io" size="10" name="blog">
            </div>
        </div>

		<hr color='#EAEAEA' style="margin-bottom: 2%;">

		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type='submit' class="btn btn-info" value='회원가입'> &nbsp;&nbsp; 
				<input type='button' class="btn btn-danger" OnClick='Reset()' value='다시 쓰기'>
			</div>
		</div>
	</FORM>
	<br>
</div>

<script>
	function Reset() {
		join.member_id.value = "";
		join.passwd.value = "";
		join.passwdConfirm.value = "";
		join.nick_name.value = "";
		join.phone1.value = "";
		join.phone2.value = "";
		join.email1.value = "";
		join.email2.value = "";
	}

	function Check() {
		if (join.member_id.value.length < 1) {
			alert("아이디를 입력하세요.");
			join.member_id.focus();
			return false;
		}

		if (join.member_id.value.length < 4) {
			alert("아이디는 4글자 이상이어야 합니다.");
			join.member_id.focus();
			return false;
		}

		if (join.passwd.value.length < 1) {
			alert("비밀번호를 입력하세요.");
			join.passwd.focus();
			return false;
		}

		if (join.passwd.value != join.passwdConfirm.value) {
			alert("비밀번호가 일치하지 않습니다..");
			join.passwdConfirm.focus();
			return false;
		}

		if (join.nick_name.value.length < 1) {
			alert("닉네임을 입력하세요.");
			join.nick_name.focus();
			return false;
		}
		if (join.idCheck.value.length < 1) {
			alert("아이디 중복을 확인해주세요.");
			return false;
		}
		join.submit();
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
		$("#checkIdBtn").on("click", function() {    
	         if(!$("#checkId").val()){
	             alert("내용을 입력해 주세요.");
	             return;
	         }                
	         var userId = $("#checkId").val();  
	     
	         $.ajax({
	             type : 'post',
	             url : '/user/checkId/'+userId,
	             headers : {
	                 "Content-Type" : "application/json",
	                 "X-HTTP-Method-Override" : "POST"
	             },
	             dataType : 'text',             
	             success : function(result) {	            	 
	                 if (result == userId)     
	                     alert("이미 등록된 아이디입니다.");                     
	                 else
	                     alert("사용가능한 아이디입니다.");                
	             }
	         });
	     });
	});
	
</script>
<%@ include file="../template/footer.jsp"%>