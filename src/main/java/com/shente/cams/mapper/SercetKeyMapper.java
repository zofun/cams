package com.shente.cams.mapper;

import com.shente.cams.pojo.SercetKey;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author sugar
 * 2019/11/22
 * 下午3:28
 * 类作用描述
 */

@Mapper
@Repository
public interface SercetKeyMapper {

    @Insert("insert into ciphertext(user_id,text,course_id) values(#{userId},#{text},#{courseId})")
    int insert(SercetKey key);

    @Select("select text from ciphertext where course_id=#{courseId}")
    String selectTextByCourseId(Integer courseId);

    @Update("update ciphertext set text = #{text} where course_id= #{courseId}")
    int updateTextByCourseId(Integer courseId,String text);

    @Select("select course_id from ciphertext where text = #{key}")
    Integer selectCourseIdByKey(String key);
}
