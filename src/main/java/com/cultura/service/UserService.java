package com.cultura.service;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;

public interface UserService {
    public void createIdentification(UserVO user, AuthVO auth) throws Exception;
}
