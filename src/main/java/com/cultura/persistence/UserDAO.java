package com.cultura.persistence;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;

public interface UserDAO {
    public String readId(String id) throws Exception;
    public String readNickname(String nickname) throws Exception;
    public void createUser(UserVO user) throws Exception;
    public void createAuth(AuthVO auth) throws Exception;
}
