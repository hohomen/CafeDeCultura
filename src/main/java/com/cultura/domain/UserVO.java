package com.cultura.domain;

import java.util.Date;
import java.util.List;

public class UserVO {
    private String userId;
    private String userPw;
    private String nickname;
    private String email;
    private Date regDate;
    private Date updateDate;
    private boolean enabled;
    private List<AuthVO> authList;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserPw() {
        return userPw;
    }
    public void setUserPw(String userPw) {
        this.userPw = userPw;
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
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public List<AuthVO> getAuthList() {
        return authList;
    }
    public void setAuthList(List<AuthVO> authList) {
        this.authList = authList;
    }
    
    
}
