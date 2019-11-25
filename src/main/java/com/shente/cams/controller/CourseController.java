package com.shente.cams.controller;

import com.shente.cams.dto.Result;
import com.shente.cams.pojo.Course;
import com.shente.cams.service.CourseService;
import com.shente.cams.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    @PutMapping(value = "course")
    public Result changeCourseStatus(@RequestBody Course course){
        try {
            courseService.updateCourseState(course);
            return new Result().success(null);
        }catch (IOException e){
            e.printStackTrace();
            return new Result().fail("排课出错，课程关闭失败",500);
        }catch (RuntimeException e){
            e.printStackTrace();
            return new Result().fail(e.getMessage(),500);
        }
    }


    /**
     * 处理教师新建课程的请求
     * @param course
     * @return
     */
    @RequestMapping(value = "course",method = RequestMethod.POST)
    public Result addCourse(@RequestBody Course course, @RequestHeader("auto_token")String token){
        if(TokenUtils.isValid(token)){
            course.setUserId(TokenUtils.getUserId());
        }
        try{
            course.setState(1);
            courseService.addCourse(course);
        }catch (Exception e){
            //处理已经新建过课程的异常
            return new Result().fail(e.getMessage(),2001);
        }

        return new Result().success("OK");
    }

    /**
     * 删除课程
     * @param course
     */
    @DeleteMapping(value = "course")
    public Object deleteCourse(@RequestBody Course course){
        courseService.deleteCourse(course.getCId());
        return Result.baseSuccess();
    }

    /**
     * 用户获取已创建的课程列表
     * @return
     */
    @GetMapping(value = "course")
    public Object getCourseList(@RequestHeader("auto_token")String token){
        if(TokenUtils.isValid(token)){
            List<Course> course_list = courseService.checkCourse(TokenUtils.getUserId());
            return Result.baseSuccess(course_list);
        }
        return Result.baseSuccess();
    }

    /**
     * 获取指定主键的课程信息
     * @param id
     */
    @GetMapping(value = "course/{course_id}")
    public Object getCourse(@PathVariable("course_id") Integer id){
        Course course = courseService.selectCourse(id);
        return Result.baseSuccess(course);
    }
}
