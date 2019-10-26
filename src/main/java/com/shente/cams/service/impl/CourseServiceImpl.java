package com.shente.cams.service.impl;

import com.shente.cams.mapper.CourseMapper;
import com.shente.cams.pojo.Course;
import com.shente.cams.service.CourseService;
import com.shente.cams.service.ScheduleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;

    //服务层调用服务层
    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void updateCourseState(Course course) throws IOException {
        courseMapper.updateCourseState(course.getCId(),course.getState());
        if(1==course.getState()){
            //如果课程关闭了，就开始排课

            scheduleService.coursesArranging(course.getCId());
        }
    }

    @Override
    public void addCourse(Course course) {
        List<Course> list = courseMapper.queryCourseByUserIdAndName(course.getUserId(), course.getName());
        if(list.size()!=0){
            throw new RuntimeException("course already exist");
        }
        courseMapper.insert(course);
    }
}
