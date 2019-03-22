<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../template/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>


<!-- Header with Background Image -->
<header class="business-header">
	<div class="container">
		<div class="row">
			<div class="col-lg-12"></div>
		</div>
	</div>
</header>

<!-- Header with phrase and search bar -->
<div class='row'>
	<div class="col-md-7 header2"
		style="text-align: center; padding: 10px;">
		<h2 style="color: c0392b;">
			<strong class=mainfont>문화인을 위한 공간</strong>
		</h2>
	</div>
	<div class="col-md-5 header2"
		style="text-align: center; padding: 10px;">
		<a href='/board/register'><button class="btn btn-info">
				<h5>글쓰기</h5>
			</button></a>
	</div>
</div>

<div class="container col-md-10 vertical-center" >
	<div class=row style="height: 10px; padding: 3%;">
		<div class="col-md-8">
			<form class="form-inline">
				<select name="searchType" class="browser-default custom-select">
					<option value="n"
						<c:out value="${cri.searchType == null? 'selected':''}"/>>---</option>
					<option value="t"
						<c:out value="${cri.searchType eq 't'? 'selected':''}"/>>제목</option>
					<option value="c"
						<c:out value="${cri.searchType eq 'c'? 'selected':''}"/>>내용</option>
					<option value="w"
						<c:out value="${cri.searchType eq 'w'? 'selected':''}"/>>닉네임</option>
					<option value="tc"
						<c:out value="${cri.searchType eq 'tc'? 'selected':''}"/>>제목+내용</option>
				</select>

				<div class="form-group">
					<input type="text" name="keyword" class="form-control" id='keywordInput' value='${cri.keyword }' placeholder="검색어를 임력해 주세요.">
				</div>
				<div class="form-group">
					<button type="submit" id='searchBtn' class="btn btn-default">찾기</button>
				</div>
		</div>
		</form>

		<div class="form-group col-md-4">
			<button type="submit" class="btn btn-default pull-right">글쓰기</button>
		</div>
	</div>
	
	<br><br>


	<!-- Board lists -->

    <table border=1 class='table1'>
        <c:forEach items="${list}" var="boardVO">
            <tr style="height: 6em;">
                
                <td style="width: 87%; padding-left: 2em;"><h5>
                <!--uriComponant는 문제 없었다. 여기서 재대로된 처리가 안 되는거임.  -->                
                <a href='/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&board_id=${boardVO.board_id}'> &nbsp;${boardVO.title}
                    </h5> &nbsp; ${boardVO.member_id}&nbsp; <small class="text-muted"><fmt:formatDate
                            pattern="yyyy-MM-dd HH:mm" value="${boardVO.reg_date}" /> </small></td>
                </a>
                <td style="width: 13%; text-align: center;"><h3>${boardVO.view_cnt}</h3>댓글</td>
            </tr>

        </c:forEach>
    </table>



	<!-- 페이징 -->

	<div class="row">
		<div class="mx-auto justify-content-center ">
			<ul class="pagination">

				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a
						href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }"
						class="page-link">&laquo;</a></li>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="idx">
					<li class="page-item"
						<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
						<a href="list${pageMaker.makeSearch(idx)}" class="page-link">${idx}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-item"><a
						href="list${pageMaker.makeSearch(pageMaker.endPage +1) }"
						class="page-link">&raquo;</a></li>
				</c:if>

			</ul>
		</div>
	</div>

</div>

<script>
    //검색
    $(document).ready(function() {
    	    	
    	/* var keywordd = $('#keywordInput').val();    	
    	$('#keywordInput').attr('value', encodeURIComponent(keywordd)); */

        $("#searchBtn").on("click", function(event) {
                    self.location = "list"// Sets the location of the current window itself.
                    + '${pageMaker.makeQuery(1)}'
                    + "&searchType="
                    + $("select option:selected").val()
                    + "&keyword=" + encodeURIComponent($('#keywordInput').val());                     
        });

        /* $(".btn-info").on("click", function() {
            formObj.attr("action", "/board/remove");
            formObj.submit();
        }); */   

    });
</script>

<%@ include file="../template/footer.jsp"%>