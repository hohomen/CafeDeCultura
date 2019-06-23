package com.cultura.service;

import javax.inject.Inject;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cultura.model.AuthVO;
import com.cultura.model.UserVO;
import com.cultura.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {    
    @Inject
    private UserDAO dao;
    @Inject
    private BCryptPasswordEncoder passwordEncoder;    
    
    @Transactional
    @Override
    public void createID(UserVO user, AuthVO auth) throws Exception {                
        if(user.getImage().equals("")){
            user.setImage("/defaultProfile.jpg");
        }
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));        
        dao.createUser(user);
        
        auth.setAuth("USER");
        dao.createAuth(auth);  
    }
        
    @Override
    public void modifyUserInfo(UserVO user) throws Exception {
        dao.updateUser(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,user.getUserId()));
    }
    
    @Inject
    private UserDetailsService userDetailsService;     
    protected Authentication createNewAuthentication(Authentication currentAuth, String username) {
        UserDetails newPrincipal = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }

    @Override
    public UserVO isUsableId(String id) throws Exception {
        return dao.readId(id);
    }

    @Override
    public UserVO isUsableNickname(String nickname) throws Exception {
        return dao.readNickname(nickname);
    }

    @Override
    public UserVO getUserInfo(String userId) throws Exception {
        return dao.readUser(userId);
    }

    @Override
    public void deleteImage(String userId) throws Exception {
        dao.deleteImage(userId);
        if(!userId.equals("none")){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,userId));            
        }        
    }
    
}
