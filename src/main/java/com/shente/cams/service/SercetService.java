package com.shente.cams.service;

import com.shente.cams.pojo.SercetKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sugar
 * 2019/11/22
 * 下午3:26
 * 类作用描述
 */

public interface SercetService {


    /**
     * 新增秘钥内容
     */
    int addKey(SercetKey key);

    /**
     * 查询指定课程的秘钥
     * @param courseId
     * @return
     */
    String checkKey(Integer courseId);

    /**
     * 更新自定课程的秘钥
     * @param courseId
     * @param text
     * @return
     */
    int updateKey(Integer courseId,String text);

    /**
     * 通过秘钥查询对应课程的id
     * @param key
     * @return
     */
    Integer checkCourseId(String key);
}
