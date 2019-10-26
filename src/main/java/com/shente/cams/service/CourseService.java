package com.shente.cams.service;

import com.shente.cams.pojo.Course;

import java.io.IOException;

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
}
