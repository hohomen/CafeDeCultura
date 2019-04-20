package com.cultura.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;
import com.cultura.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {    
    @Inject
    private UserDAO dao;
    @Inject
    private BCryptPasswordEncoder passwordEncoder;    
    
    @Transactional
    @Override
    public void createIdentification(UserVO user, AuthVO auth) throws Exception {
        String encodePass = passwordEncoder.encode(user.getUserPw());
        user.setUserPw(encodePass);
        dao.createUser(user);
        auth.setAuth("USER");
        dao.createAuth(auth);  
    }
}
