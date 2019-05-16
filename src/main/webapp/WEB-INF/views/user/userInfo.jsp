<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<sec:authentication property="principal.user.nickname" var="authNickname"/>
<sec:authentication property="principal.user.email" var="authEamil"/>
<sec:authentication property="principal.user.userId" var="authUserId"/>

<div class='container card'>
	<div class="row jumbotron text-dark">
		<h2>
			<strong>회원 정보</strong>
		</h2>
	</div>
			    
		<div class="row">
			<strong><label for="userId" class="text-dark">&nbsp;ID </label></strong> </div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="text" id="userId" name="userId" value="${authUserId}" readonly="readonly" class="form-control">							    
			</div>
		</div>		
		<div class="row">
			<strong><label for="nickname" class="text-dark">&nbsp;닉네임</label></strong>
		</div>
		<div class="row">
			<div class="form-group col-md-6 form-check-inline">
				<input type="text" id="nickname" name="nickname" value="${authNickname}" required class="form-control">&nbsp;
				<input type="button" id="checkNicknameBtn" value="중복 검사" class="btn btn-coffee" >
				<input type='hidden' name='nicknameCheck' value=''>
			</div>
		</div>
		<div class="row">
            <strong><label for="email" class="text-dark">&nbsp;이메일</label></strong>
        </div>
        <div class="row">
            <div class="form-group col-md-6 form-check-inline">
                <input type="text" id="email" name="email" value="${authEamil}" required placeholder="ex) donystack@gmail.com" class="form-control">
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
				<input type='button' value='정보 수정' class="btn btn-info" > &nbsp;&nbsp; 
				<input type='button' value='비밀번호 변경' class="btn btn-danger">
			</div>
		</div>
	<br>
</div>
<script>
    var userId = $('#userId').val();    
    $(".btn-info").on("click", function() {
    var nickname = $('#nickname').val();
    var email = $('#email').val();
        $.ajax({
            type:'put',
            url:'/user/modify/'+ userId,
            headers: { 
                  "Content-Type": "application/json",
                  "X-HTTP-Method-Override": "PUT" },
            data:JSON.stringify({
            	userId : userId,        
            	nickname : nickname,
            	email : email            	
            }),
            dataType:'text',
            success:function(result){
                console.log("result: " + result);
                if(result == 'SUCCESS'){
                	location.reload()
                    alert("수정 되었습니다.");
                }
            }
        });
    });    

</script>
<%@ include file="../template/footer.jsp"%>