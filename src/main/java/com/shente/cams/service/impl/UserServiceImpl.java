package com.shente.cams.service.impl;
/*
 *@author sugar
 *2019/10/28
 *14:20
 */

import com.shente.cams.mapper.UserMapper;
import com.shente.cams.pojo.User;
import com.shente.cams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
       return userMapper.insert(user);
    }

    @Override
    public User checkUser(User user) {
        return userMapper.selectUserByAccount(user.getAccount());
    }
}
