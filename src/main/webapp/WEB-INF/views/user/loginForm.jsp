<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>

    <div class=" jumbotron text-dark" >
            <h2 align="center"><strong>로그인</strong></h2>
    </div>
    <h2>${error}</h2>    
<div class="col-xd-4" align="center">
    <div class="col-md-3">
        <form action="/login" method="post">
            
            <div class="form-group text-dark" align="left">
                <strong><label for="j_username">이메일 or 아이디</label></strong> <input
                    name="j_username" value='' id="j_username"
                    placeholder="E-mail or Username" type="text" class="form-control" />
            </div>
            <div class="form-group text-dark" align="left">
                <strong><label for="j_password">비밀번호</label></strong> <input
                    name="j_password" id="j_password" value='' placeholder="Password"
                    type="password" class="form-control" />
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-warning btn-block m-t-md text-dark" value="Login" />
                <!-- <input type="submit" Onclick="check()" class="btn btn-warning btn-block m-t-md text-dark" value="Login" /> -->
            </div>
            <hr />
        </form>
        <div class="form-group">
            <a href="/user/joinForm">
                <button class="btn btn-info btn-block text-dark">회원가입</button>
            </a>
        </div>
    </div>
</div>

<%@ include file="../template/footer.jsp"%>