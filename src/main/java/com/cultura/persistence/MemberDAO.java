package com.cultura.persistence;

import com.cultura.domain.MemberVO;
import com.cultura.dto.LoginDTO;

public interface MemberDAO {
	
	public MemberVO login(LoginDTO dto)throws Exception;
	
}
