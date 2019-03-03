package com.cultura.domain;

public class MemberVO {
	private String member_id;
	private String passwd;
	private String nickname;
	private String email;
	private int member_point;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMember_point() {
		return member_point;
	}
	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}
	
	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", passwd=" + passwd + ", nickname=" + nickname + ", email=" + email
				+ ", member_point=" + member_point + "]";
	}
}
