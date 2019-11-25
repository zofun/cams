package com.shente.cams.controller;


import com.shente.cams.dto.Result;
import com.shente.cams.pojo.TResult;
import com.shente.cams.service.ResultService;
import com.shente.cams.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("course_res")
public class ResultController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ResultService resultService;

    private Map<String,Object> map=new HashMap<>();
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

    /**
     * 添加空闲结果数据
     */
    @PostMapping("res")
    public Object addResult(@RequestBody TResult tResult){
        tResult.setResultType('S');
        map.clear();
        map.put("id",resultService.addResult(tResult));
        return Result.baseSuccess(map);
    }


    /**
     * 获取填写的空闲数据
     */
    @GetMapping("res/{course_id}/{account}")
    public Object checkResult(@PathVariable("course_id") Integer courseId,@PathVariable("account") String account){
        TResult result = resultService.getResultByAccount(courseId, account);
        if(result==null){
            return Result.baseError(4001,"data not exist");
        }
        return Result.baseSuccess(result);
    }

    /**
     * 更新选课数据内容
     */
    @PutMapping("res")
    public Object updateResult(@RequestBody TResult tResult){
        resultService.updateResult(tResult);
        return Result.baseSuccess();
    }

    @GetMapping("res/{course_id}")
    public Object getResultList(@PathVariable("course_id") Integer courseId){
        List<TResult> resultList = resultService.getPeopleData(courseId);
        return Result.baseSuccess(resultList);
    }
}
