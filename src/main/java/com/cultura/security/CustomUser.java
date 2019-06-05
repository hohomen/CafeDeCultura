package com.cultura.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.cultura.model.UserVO;
public class CustomUser extends User {

    private static final long serialVersionUID = 1L;
    private UserVO user;

    public CustomUser(String username, String password, 
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(UserVO vo) {
        super(vo.getUserId(), vo.getUserPw(), vo.getAuthList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
        this.setUser(vo);
    }

    public UserVO getUser() {
        return user;
    }
    public void setUser(UserVO user) {
        this.user = user;
    }
}
