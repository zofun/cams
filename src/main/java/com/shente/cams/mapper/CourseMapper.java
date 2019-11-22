package com.shente.cams.mapper;

import com.shente.cams.pojo.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {

    Course queryCourseById(@Param("id") Integer id);

    void updateCourseState(@Param("id")Integer id,@Param("newstate") Integer newState);

    void insert(Course course);

    List<Course> queryCourseByUserIdAndName(@Param("id") Integer id,@Param("name") String name);

    /**
     * 通过id删除
     * @param id
     */
    @Delete("delete from course where c_id = #{id}")
    int deleteById(Integer id);

    @Select("select c_id,name,state from course where user_id = #{id}")
    List<Course> selectCoursesByUserId(Integer id);

    @Select("select * from course where c_id = #{cId}")
    Course selectCourseById(Integer cId);

}
