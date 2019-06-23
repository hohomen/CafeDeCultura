package com.cultura.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.model.AuthVO;
import com.cultura.model.SearchCriteria;
import com.cultura.model.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
    @Inject
    private SqlSession session;    
    private static String namespace = "com.cultura.mapper.UserMapper";
    
    @Override
    public UserVO readId(String id) throws Exception {
        return session.selectOne(namespace + ".readId", id);
    }
    
    @Override
    public UserVO readNickname(String nickname) throws Exception {
        return session.selectOne(namespace + ".readNickname", nickname);
    }

    @Override
    public void createUser(UserVO user) throws Exception {
        session.insert(namespace+".createUser", user);
    }

    @Override
    public void createAuth(AuthVO auth) throws Exception {
        session.insert(namespace+".createAuth", auth);
    }

    @Override
    public void updateUser(UserVO user) throws Exception {
        session.update(namespace+".updateUserInfo", user);        
    }

    @Override
    public UserVO readUser(String id) throws Exception {
        return session.selectOne(namespace+".readUserInfo", id);
    }

    @Override
    public String selectUserId(String keyword) throws Exception {
        return session.selectOne(namespace+".selectUserId", keyword);
    }

    @Override
    public void deleteImage(String userId) throws Exception {        
        session.update(namespace+".deleteImage", userId);
    }
    
}
