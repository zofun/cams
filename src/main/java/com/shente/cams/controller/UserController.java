package com.shente.cams.controller;
/*
 *@author sugar
 *2019/10/28
 *14:37
 */

import com.shente.cams.dto.Result;
import com.shente.cams.pojo.User;
import com.shente.cams.service.UserService;
import com.shente.cams.util.LocalCache;
import com.shente.cams.util.TokenUtils;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    private Map<String,Object> res=new HashMap<>();

    /**
     * 用户注册接口
     * @param user 包含(account,password)
     */
    @PostMapping("register")
    public Object register(@RequestBody User user){
        User originUser = userService.checkUser(user);
        if(originUser==null){
            userService.insert(user);
            return Result.baseSuccess();
        }else {
            return Result.baseError(1011,"account already exist");
        }
    }

    /**
     * 用户登录
     * @param user (账号,面)
     */
    @PostMapping("login")
    public Object login(@RequestBody User user) throws Exception {
        User originUser=userService.checkUser(user);
        //用户不存在
        if(originUser==null){
            return Result.baseError(1002,"account not exist");
        }
        //密码错误
        if(!originUser.getPassword().equals(user.getPassword())){
            return Result.baseError(1001,"password error");
        }
        //登陆成功
        res.clear();
        String s = TokenUtils.create(originUser.getUId(), originUser.getAccount(), 3600);
        s=s.replace("\n","");
        String key=s.substring(s.length()-20,s.length()-5);
        LocalCache localCache=new LocalCache();
        localCache.putValue(key,s,3600);
        res.put("token", key);
        return Result.baseSuccess(res);
    }
}
