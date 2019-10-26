package com.shente.cams.controller;

import com.shente.cams.dto.Result;
import com.shente.cams.pojo.Course;
import com.shente.cams.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("course")
public class CourseController {


    @Autowired
    private CourseService courseService;


    /**
     * 处理更改课程状态的请求
     * @param course
     * @return
     */
    @RequestMapping(value = "course",method = RequestMethod.PUT)
    public Result changeCourseStatus(@RequestBody Course course){

        try {
            courseService.updateCourseState(course);
            return new Result().success(null);
        }catch (IOException e){
            e.printStackTrace();
            return new Result().fail("排课出错，课程关闭失败",500);
        }
    }


    /**
     * 处理教师新建课程的请求
     * @param course
     * @return
     */
    @RequestMapping(value = "course",method = RequestMethod.POST)
    public Result addCourse(@RequestBody Course course){
        try{
            courseService.addCourse(course);
        }catch (Exception e){
            //处理已经新建过课程的异常
            return new Result().fail(e.getMessage(),2001);
        }

        return new Result().success("OK");
    }


    /**
     * 用户获取已创建的课程列表
     * @return
     */
    @RequestMapping(value = "course",method = RequestMethod.GET)
    public Result getCourseList(){


        return new Result();
    }
}
