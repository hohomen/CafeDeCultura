package com.cultura.model;

import java.util.Date;

public class ReplyVO {
    private Integer replyId;    
    private String replyText;
    private String replyer;
    private Date regDate;
    private Integer boardId;
    private String nickname;
    private String reParent;
    private String reDepth;
    private Integer reOrder;
    private String image;
        
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getReParent() {
        return reParent;
    }
    public void setReParent(String reParent) {
        this.reParent = reParent;
    }
    public String getReDepth() {
        return reDepth;
    }
    public void setReDepth(String reDepth) {
        this.reDepth = reDepth;
    }
    public Integer getReOrder() {
        return reOrder;
    }
    public void setReOrder(Integer reOrder) {
        this.reOrder = reOrder;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Integer getReplyId() {
        return replyId;
    }
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
    public Integer getBoardId() {
        return boardId;
    }
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getReplyText() {
        return replyText;
    }
    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    public String getReplyer() {
        return replyer;
    }
    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }
    
}
