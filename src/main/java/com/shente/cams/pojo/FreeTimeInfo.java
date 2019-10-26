package com.shente.cams.pojo;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 记录学生的空闲信息
 */
@ToString
public class FreeTimeInfo{

    public Map<String,Object> map;

    public FreeTimeInfo(){
        map=new HashMap<String,Object>();
    }

}
