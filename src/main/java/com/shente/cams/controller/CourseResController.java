package com.shente.cams.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shente.cams.dto.Result;
import com.shente.cams.pojo.TResult;
import com.shente.cams.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("course_res")
public class CourseResController {


    @Autowired
    private ScheduleService scheduleService;


    /**
     * 处理添加学生的空闲时间
     * @param result
     * @return
     */
    @RequestMapping("res")
    public Result addStuFreeTimeInfo(@RequestBody TResult result){
        int id=scheduleService.addStuFreeTimeInfo(result.getAccount(),result.getCourseId(),result.getResultData());
        return new Result().success(id);
    }
}
