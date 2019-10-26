package com.shente.cams.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @author TCW
 * 排课服务
 */
public interface ScheduleService {


    /**
     * 执行排课算法
     * @param courseId
     * @return
     * @throws IOException
     */
    String coursesArranging(Integer courseId) throws IOException;

    /**
     * 添加学生的统计信息到数据库
     * @param account
     * @param courseId
     * @param json
     * @return
     */
    int addStuFreeTimeInfo(String account,Integer courseId,String json);
}
