package com.shente.cams.service;

import com.shente.cams.pojo.Course;

import java.io.IOException;
import java.util.List;

public interface CourseService {


    /**
     * 更新课程的状态
     * @param course
     */
    void updateCourseState(Course course) throws IOException;

    /**
     * 新建课程
     * @param course
     */
    void addCourse(Course course);

    /**
     * 删除课程
     * @param id
     */
    int deleteCourse(Integer id);

    /**
     * 查询课程列表
     * @param id
     */
    List<Course> checkCourse(Integer id);

    /**
     * 查询指定课程信息
     * @param cId
     */
    Course selectCourse(Integer cId);
}
