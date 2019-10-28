package com.shente.cams.service;

import com.shente.cams.pojo.User;

public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insert(User user);


    /**
     * 查询用户
     * @param user
     * @return
     */
    User checkUser(User user);


}
