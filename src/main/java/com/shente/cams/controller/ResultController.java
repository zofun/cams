package com.shente.cams.controller;


import com.shente.cams.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {

    @Autowired
    private ScheduleService scheduleService;


    /**
     * 收集学生的统计信息
     * @param account
     * @param courseId
     * @param json
     */
    @RequestMapping("/add")
    public void addStuFreeTimeResult(String account,Integer courseId,String json){
        scheduleService.addStuFreeTimeInfo(account,courseId,json);
    }

    public String getSchduleResult(Integer courseId){
        return null;
    }

    public String beginSchdule(Integer courseId){
        return null;
    }
}
