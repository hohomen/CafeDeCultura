package com.cultura.persistence;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
    @Inject
    private SqlSession session;    
    private static String namespace = "com.cultura.mapper.UserMapper";
    
    @Override
    public String readId(String id) throws Exception {
        return session.selectOne(namespace + ".readId", id);
    }

    @Override
    public void createUser(UserVO vo) throws Exception {
        session.insert(namespace+".createUser", vo);
    }

    @Override
    public void createAuth(UserVO vo) throws Exception {
        // TODO Auto-generated method stub
        
    }    
}
