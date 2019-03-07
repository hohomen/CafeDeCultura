package com.cultura.service;

import com.cultura.domain.MemberVO;
import com.cultura.dto.LoginDTO;

public interface MemberService {
    
    public MemberVO login(LoginDTO dto) throws Exception;
}
