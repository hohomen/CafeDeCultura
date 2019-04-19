package com.cultura.persistence;

import com.cultura.domain.UserVO;

public interface UserDAO {
    public String readId(String id) throws Exception;
    
    public void createUser(UserVO vo) throws Exception;
    public void createAuth(UserVO vo) throws Exception;
}

