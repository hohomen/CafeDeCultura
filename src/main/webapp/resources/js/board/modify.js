$(document).ready(function() {
    const formObj = $("form[role='form']");    
    
    //취소
    $("#modifyCancelBtn").on("click", function(){
        self.location = "/board/read?page=${cri.page}&perPageNum=${cri.perPageNum}"
                        +"&searchType=${cri.searchType}&keyword=${cri.keyword}"
                        +"&boardId=+${boardVO.boardId}";
    });
    
    //수정
    $("#modifyWriteBtn").on("click", function(){
        formObj.submit();
    });
});