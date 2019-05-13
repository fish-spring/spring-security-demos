package com.spring4all.service;

import com.spring4all.entity.User;

public interface UserService {

    /**
     * 添加新用户
     *
     * username 唯一， 默认 USER 权限
     */
    User insert(User user);

    User update(User user);

    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    User getByUsername(String username);



}
