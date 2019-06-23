package com.cultura.service;

import com.cultura.model.AuthVO;
import com.cultura.model.UserVO;

public interface UserService {
    public void createID(UserVO user, AuthVO auth) throws Exception;
    public UserVO isUsableId(String id)throws Exception;
    public UserVO isUsableNickname(String nickname)throws Exception;
    public void modifyUserInfo(UserVO user) throws Exception;
    public UserVO getUserInfo(String id) throws Exception;
    public void deleteImage(String userId) throws Exception;
}
