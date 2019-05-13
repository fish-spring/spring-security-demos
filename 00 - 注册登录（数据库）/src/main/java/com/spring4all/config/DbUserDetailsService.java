package com.spring4all.config;

import com.spring4all.entity.User;
import com.spring4all.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    DbUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }

        log.info("查询到用户" + user.toString());

        // 解析出用户的角色
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : user.getRoles().split(" ")){
            log.info(role);
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        log.info(simpleGrantedAuthorities.toString());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorities);
    }

}
