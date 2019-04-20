package com.cultura.security;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cultura.aop.LogAdvice;
import com.cultura.domain.UserVO;



public class CustomUserDetailsService implements UserDetailsService {
    @Inject
    private SqlSession session;
    private static String namespace = "com.cultura.mapper.UserMapper";
    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);  
    
    
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {	    
	    
		logger.warn("Load User By UserName : " + userName);		
		UserVO vo = session.selectOne(namespace+".read", userName);
		logger.warn("queried by member mapper: " + vo);
		return vo == null ? null : new CustomUser(vo);
	} 

}
