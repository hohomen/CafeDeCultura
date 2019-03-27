package com.cultura.domain;

import java.util.Date;

public class BoardVO {
	private Integer board_id;
	private String title;
	private String content;
	private Date reg_date;
	private int view_cnt;
	private String member_id;
	private int reply_cnt;
	
	
	public int getReply_cnt() {
        return reply_cnt;
    }
    public void setReply_cnt(int reply_cnt) {
        this.reply_cnt = reply_cnt;
    }
    public Integer getBoard_id() {
		return board_id;
	}
	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
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
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
				+ ", view_cnt=" + view_cnt + ", member_id=" + member_id + "]";
	}
	
	
}
