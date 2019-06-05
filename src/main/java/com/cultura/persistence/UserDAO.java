package com.cultura.persistence;

import com.cultura.model.AuthVO;
import com.cultura.model.UserVO;

public interface UserDAO {
    public UserVO readId(String id) throws Exception;
    public UserVO readNickname(String nickname) throws Exception;
    public void createUser(UserVO user) throws Exception;
    public void createAuth(AuthVO auth) throws Exception;
    public void updateUser(UserVO user) throws Exception;
    public UserVO readUser(String id)throws Exception;
}

