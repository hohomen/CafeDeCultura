package com.cultura.controller;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cultura.model.AuthVO;
import com.cultura.model.UserVO;
import com.cultura.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                    "file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class UserTest {
    @Inject
    private UserDAO dao;
    private static Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test @Ignore
    public void testReadId() {
        try {
            logger.info(dao.readId("cho").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test @Ignore
    public void testReadNickname() {
        try {
            logger.info(dao.readNickname("ÃÖµ¿").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test @Ignore
    public void testJoinResgister() {
        UserVO user = new UserVO();
        user.setUserId("fabregas");
        user.setUserPw("fabregas");
        user.setNickname("fabregas");
        user.setEmail("fabregas");        
        
        try {
            dao.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    @Test @Ignore
    public void testCreateAuth(){
        AuthVO auth = new AuthVO();
        auth.setUserId("jebal");
        auth.setAuth("USER");
        
        try {
            dao.createAuth(auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
    @Test
    public void testUpdateUser(){
        UserVO user = new UserVO();        
        user.setNickname("Çï·Î¿ì");
        user.setEmail("choi3");
        user.setUserId("choi3");
        try {
            dao.updateUser(user);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testReadUser(){
        String userId = "choi3";
        try {
            dao.readUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
