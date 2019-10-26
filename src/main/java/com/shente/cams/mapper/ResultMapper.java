package com.shente.cams.mapper;

import com.shente.cams.pojo.TResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResultMapper {

    int insert(TResult result);

    /**
     * 根据课程id获取结果
     * @param courseId
     * @param type 区分是排课结果还是调查结果
     * @return
     */
    List<String> queryResultDataByCourseId(@Param("courseId") int courseId,@Param("type") char type);
}
