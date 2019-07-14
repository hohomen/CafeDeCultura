package com.cultura.model;

import java.util.Date;

public class BoardVO {
	private Integer boardId;
	private String title;
	private String content;
	private Date regDate;
	private int viewCnt;
	private int likeCnt;
	private int replyCnt;
	private String userId;
	private String nickname;
	
	
    public int getLikeCnt() {
        return likeCnt;
    }
    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Integer getBoardId() {
        return boardId;
    }
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getViewCnt() {
        return viewCnt;
    }
    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
    public int getReplyCnt() {
        return replyCnt;
    }
    public void setReplyCnt(int replyCnt) {
        this.replyCnt = replyCnt;
    }    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    @Override
    public String toString() {
        return "BoardVO [boardId=" + boardId + ", title=" + title + ", content=" + content + ", regDate=" + regDate
                + ", viewCnt=" + viewCnt + ", replyCnt=" + replyCnt + ", userId=" + userId + "]";
    }    
    
}
