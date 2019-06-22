<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./template/header.jsp"%>
<link href="https://fonts.googleapis.com/css?family=Gaegu&display=swap" rel="stylesheet">
<!-- Header with Background Image -->
<header class="business-header">
	<div class="container">
		<div class="row">
			<div class="col-lg-12"></div>
		</div>
	</div>
</header>


<!-- Header with phrase and search bar -->


<div class="container col-md-10 vertical-center" >
    <div class='row header2'>    
        <h3 style="color: c0392b;">
            <strong class="mainfont">문화인을 위한 공간</strong>
        </h3>      
</div>

    <div class=row>
        <div class="form-group col-md-6">
            <a href='/board/write'>
                <img src = "/resources/image/write.png" >
            </a>
        </div>
        <div class="col-md-6">
            <form>
            <div class="input-group">
                <select name="searchType" class="custom-form-control">                    
                    <option value="title"
                        <c:out value="${cri.searchType eq 'title'? 'selected':''}"/>>제목</option>
                    <option value="contents"
                        <c:out value="${cri.searchType eq 'contents'? 'selected':''}"/>>내용</option>
                    <option value="nickname"
                        <c:out value="${cri.searchType eq 'nickname'? 'selected':''}"/>>닉네임</option>
                    <option value="TC"
                        <c:out value="${cri.searchType eq 'TC'? 'selected':''}"/>>제목+내용</option>
                </select>
                <input type="text" name="keyword" id='keywordInput' value='${cri.keyword }' placeholder="검색어를 입력해 주세요." class="form-control">
                <button type="submit" id='searchBtn' class="btn btn-default input-group-addon">찾기</button>
             </div>
             </form>
        </div>
    </div>
</div>

<!-- Board lists -->
<div class="container col-md-10 vertical-center" >
    <table border=1 class='table1'>    
        <c:forEach items="${list}" var="boardVO">        
            <tr  style="height: 6em;">                
                <td style="width: 87%; padding-left: 2em;">
                    <h5>
                        <a href='/board/read${pageMaker.makeSearch(pageMaker.cri.page)}&boardId=${boardVO.boardId}'>${boardVO.title} </a>
                    </h5>
                    ${boardVO.nickname}&nbsp;
                    <small class="text-muted"> 
                        <fmt:formatDate pattern="yy.MM.dd HH:mm" value="${boardVO.regDate}" /> 
                        &nbsp;조회수&nbsp;${boardVO.viewCnt} 
                    </small>
                </td>             
                <td style="width: 13%; text-align: center;">
                    <h3>${boardVO.replyCnt}</h3>
                                        댓글
                </td>
            </tr>        
        </c:forEach>
    </table>
    
    <div class="row" style="margin:3%;">
        <div class="mx-auto justify-content-center ">
            <ul class="pagination">
                <c:if test="${pageMaker.prev}">
                    <li class="page-item ml-2">
                        <a href="${pageMaker.makeSearch(pageMaker.startPage - 1) }"
                           class="page-link">&laquo;</a></li>
                </c:if>

                <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
                    <li class="page-item ml-2 <c:out value="${pageMaker.cri.page == idx?'active':''}"/>">
                        <a href="${pageMaker.makeSearch(idx)}" class="page-link">${idx}</a>
                    </li>
                </c:forEach>
                
                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                    <li class="page-item ml-2 ">
                        <a href="${pageMaker.makeSearch(pageMaker.endPage +1) }"
                           class="page-link">&raquo;</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <a href='/board/write'>
                <img src="/resources/image/write_small.png" >
        </a>
    </div>    
</div>
<%@ include file="./template/footer.jsp"%>