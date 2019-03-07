package com.cultura.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.domain.MemberVO;
import com.cultura.dto.LoginDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Inject
    private SqlSession session;
    
    private static String namespace = "com.cultura.mapper.MemberMapper";
    @Override
    public MemberVO login(LoginDTO dto) throws Exception {
        
        return session.selectOne(namespace + ".login", dto);
    }

}
