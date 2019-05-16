package com.cultura.service;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;

public interface UserService {
    public void createIdentification(UserVO user, AuthVO auth) throws Exception;
    public UserVO isUsableId(String id)throws Exception;
    public UserVO isUsableNickname(String nickname)throws Exception;
    public void modifyUserInfo(UserVO user) throws Exception;
    public UserVO getUserInfo(String id) throws Exception;
}
