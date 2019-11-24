package com.shente.cams.controller;

import com.shente.cams.dto.Result;
import com.shente.cams.pojo.SercetKey;
import com.shente.cams.service.SercetService;
import com.shente.cams.util.DesUtil;
import com.shente.cams.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sugar
 * 2019/11/22
 * 下午3:40
 * ciphertext控制器
 */

@RestController
@RequestMapping("ciphertext")
public class CiphertextController {

    @Autowired
    private SercetService sercetService;

    private Map<String,Object> map =new HashMap<>();

    /**
     * 新建秘钥
     */
    @PostMapping("ciphertext")
    public Object addKey(@RequestHeader("auto_token")String token, @RequestBody SercetKey key) throws Exception {
        if(TokenUtils.isValid(token)){
//            设置用户
            key.setUserId(TokenUtils.getUserId());
        }
        String text= DesUtil.encrypt(key.toString()+new Date().getTime());
        text=text.substring(text.length()-12,text.length()-4);
        map.clear();
        map.put("text",text);
//        设置秘钥
        key.setText(text);
        sercetService.addKey(key);
        return Result.baseSuccess(map);
    }

    /**
     * 更新指定课程的秘钥
     */
    @PutMapping("ciphertext")
    public Object updateKey(@RequestHeader("auto_token")String token, @RequestBody SercetKey key) throws Exception {
        if(TokenUtils.isValid(token)){
            key.setUserId(TokenUtils.getUserId());
        }
        String text= DesUtil.encrypt(key.toString()+new Date().getTime());
        text=text.substring(text.length()-12,text.length()-4);
        map.clear();
        map.put("ciphertext",text);
        sercetService.updateKey(key.getCourseId(),text);
        return Result.baseSuccess(map);
    }


    /**
     * 获取指定课程的秘钥
     */
    @GetMapping("ciphertext/{course_id}")
    public Object getKey(@PathVariable("course_id") Integer courseId){
        map.clear();
        String key=sercetService.checkKey(courseId);
        if(key==null|| key.trim().equals("")){
            return Result.baseError(3001,"not exist ciphertext");
        }
        map.put("ciphertext",key);
        return Result.baseSuccess(map);
    }

    /**
     * 通过秘钥获取课程的id
     */
    @GetMapping("ciphertext")
    public Object getCourseIdByKey(String text){
        map.clear();
        Integer courseId = sercetService.checkCourseId(text);
        if(courseId==null){
            return Result.baseError(3002,"ciphertext not exist");
        }
        map.put("courseId",courseId);
        return Result.baseSuccess(map);
    }

}
