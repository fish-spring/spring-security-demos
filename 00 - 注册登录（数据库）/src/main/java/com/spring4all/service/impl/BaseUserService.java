package com.spring4all.service.impl;

import com.spring4all.entity.User;
import com.spring4all.repository.UserRepository;
import com.spring4all.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Slf4j
public class BaseUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public BaseUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User insert(User user) {
        String username = user.getUsername();
        if (exist(username)){
            throw new RuntimeException("用户名已存在！");
        }
       return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 判断用户是否存在
     */
    private boolean exist(String username){
        User user = userRepository.findByUsername(username);
        return (user != null);
    }

}
