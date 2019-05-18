package com.cultura.domain;

import java.util.Date;

public class ReplyVO {
    private Integer replyId;    
    private String replytext;
    private String replyer;
    private Date regDate;
    private Integer boardId;
    private String nickname;
    
        
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
    public String getReplytext() {
        return replytext;
    }
    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }
    public String getReplyer() {
        return replyer;
    }
    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }
    
}
