<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>

    <div class=" jumbotron text-dark" >
            <h2 align="center"><strong>로그인</strong></h2>
    </div>
<div class="col-xd-4" align="center">
	<div class="col-md-3">
		<form accept-charset="UTF-8" role="form" action="LogIn_Check.jsp"
			method="post">
			<div class="form-group text-dark" align="left">
				<strong><label for="username-email">이메일 or 아이디</label></strong> <input
					name="member_id" value='' id="username-email"
					placeholder="E-mail or Username" type="text" class="form-control" />
			</div>
			<div class="form-group text-dark" align="left">
				<strong><label for="password">비밀번호</label></strong> <input
					name="passwd" id="password" value='' placeholder="Password"
					type="password" class="form-control" />
			</div>
			<div class="form-group">
				<input type="submit" Onclick="check()"
					class="btn btn-warning btn-block m-t-md text-dark" value="Login" />
			</div>
			<hr />
		</form>
		<div class="form-group">
			<a href="LogIn_Join.jsp">
				<button class="btn btn-info btn-block text-dark">회원가입</button>
			</a>
		</div>
	</div>
</div>

<%@ include file="../template/footer.jsp"%>