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
				<input type="button" id="checkIdBtn" value="중복 검사" class="btn btn-coffee">
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
				<input type="button" id="checkNicknameBtn" value="중복 검사" class="btn btn-coffee" >
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

<script src="/resources/js/joinForm.js"></script>
<%@ include file="../template/footer.jsp"%>