package com.cultura.service;

import javax.inject.Inject;

import com.cultura.domain.MemberVO;
import com.cultura.dto.LoginDTO;
import com.cultura.persistence.MemberDAO;

public class MemberServiceImpl implements MemberService {
    @Inject
    private MemberDAO dao;
    
    @Override
    public MemberVO login(LoginDTO dto) throws Exception {
        // TODO Auto-generated method stub
        return dao.login(dto);
    }
    
}
