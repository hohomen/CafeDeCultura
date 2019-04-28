package com.cultura.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;

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
    
}
