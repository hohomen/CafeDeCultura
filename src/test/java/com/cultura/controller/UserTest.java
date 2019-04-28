package com.cultura.controller;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;
import com.cultura.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                    "file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class UserTest {
    @Inject
    private UserDAO dao;
    private static Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test @Ignore
    public void testReadUser() {
        try {
            logger.info(dao.readId("choi").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test 
    public void testReadNickname() {
        try {
            logger.info(dao.readNickname("√÷µø»£").toString());
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
}
