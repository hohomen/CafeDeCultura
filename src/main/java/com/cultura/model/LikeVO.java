package com.cultura.model;

public class LikeVO {
    public LikeVO(){
    }
    
    public LikeVO(int boardId, String userId){
        this.boardId = boardId;
        this.userId = userId;
    }
    
    private String userId;
    private String regDate;
    private int boardId;    
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
