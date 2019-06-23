<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<sec:authentication property="principal.user.nickname" var="authNickname" />
<input id="originNickname" type="hidden" value = "${authNickname }">
<sec:authentication property="principal.user.email" var="authEmail" />
<sec:authentication property="principal.user.userId" var="authUserId" />
<sec:authentication property="principal.user.blog" var="authBlog" />
<sec:authentication property="principal.user.birth" var="authBirth" />
<sec:authentication property="principal.user.address" var="authAddress" />
<sec:authentication property="principal.user.image" var="authImage" />


<div class='container card'>
	<div class="row jumbotron text-dark">
		<h2>
			<strong>회원 정보</strong>
		</h2>
	</div>
	<div class="row">
		<div class="card col-md-6 ">
			<div class="card-body ">
				<h4>선택 정보</h4>
				<hr color='#EAEAEA' style="margin-bottom: 2em;">
				<div class="row">
					<strong><label for="blog" class="text-dark">&nbsp;블로그</label></strong>
				</div>
				<div class="row" style="margin-bottom: 2%";>
					<input type="text" id="blog" name="blog" value = "${authBlog }"
						placeholder="ex) https://cafeDeCultura.github.io"
						class="form-control">
				</div>
				<div class="row">
					<strong><label for="birth" class="text-dark">&nbsp;생일</label></strong>
				</div>
				<div class="row" style="margin-bottom: 2%";>
					<div class='form-group'>
						<input type="date" id="birth" name="birth" value="${authBirth}"
							class="form-control">
					</div>
				</div>
				<div class="row">
					<strong><label for="address" class="text-dark">&nbsp;활동
							지역</label></strong>
				</div>
				<div class="row" style="margin-bottom: 2%";>
					<input type="text" id="address" name="address"
						value="${authAddress}" class="form-control">
				</div>
				<div class="row">
					<strong><label for="image" class="text-dark">&nbsp;프로필
							사진</label></strong>
				</div>
				<div class="fileDrop">
					<div class="row " style="display: flex; justify-content: center;">
						<img src="/resources/image/profile/defaultProfile.jpg"
							class="img img-rounded img-fluid">
					</div>
					<br>
					<p style="text-align: center;">프로필 사진을 드래그하여 업로드하세요!</p>
				</div>
				<div class="uploadedList row"
					style="display: flex; justify-content: center;"></div>
				<input type="hidden" id="image" name="image" value="${authImage}">

			</div>
		</div>

		<div class="card col-md-6">
			<div class="card-body">
				<h4>필수 정보</h4>
				<hr color='#EAEAEA' style="margin-bottom: 2em;">
				<div class="row">
					<strong><label for="userId" class="text-dark">&nbsp;ID</label></strong>
				</div>
				<div class="row">
					<div class="form-group form-check-inline">
						<input type="text" id="userId" name="userId" value="${authUserId}"
							readonly="readonly" class="form-control">
					</div>
				</div>
				<div class="row">
					<strong><label for="nickname" class="text-dark">&nbsp;닉네임</label></strong>
				</div>
				<div class="row">
					<div class="form-group form-check-inline">
						<input type="text" id="nickname" name="nickname"
							value="${authNickname}" required class="form-control">&nbsp;
						<input type="button" id="checkNicknameBtn" value="중복 검사" class="btn btn-coffee"> 
						<input type='hidden' name='nicknameCheck' value=''>
					</div>
				</div>
				<div class="row">
					<strong><label for="email" class="text-dark">&nbsp;이메일</label></strong>
				</div>
				<div class="row" style="margin-bottom: 2%">
					<input type="text" id="email" name="email" value="${authEmail}"
						required placeholder="ex) donystack@gmail.com"
						class="form-control">
				</div>
				<!-- <div class="row">
                    <strong><label for="passwd" class="text-dark">&nbsp;비밀번호</label></strong>
                </div> -->
			</div>
		</div>

	</div>
	<div class="row justify-content-md-center" style="margin-top: 1.5em;">
		<div class="form-group">
			<input type='submit' id="userInfoModBtn" value='정보 수정' class="btn btn-info">&nbsp;&nbsp; 
			<input type='button' value='다시 쓰기'	 class="btn btn-danger">
		</div>
	</div>
	<br>
</div>


<script src="/resources/js/user/userInfo.js"></script>
<%@ include file="../template/footer.jsp"%>