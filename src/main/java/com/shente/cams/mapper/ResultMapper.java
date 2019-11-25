package com.shente.cams.mapper;

import com.shente.cams.pojo.TResult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResultMapper {

    int insert(TResult result);

    @Select("select r_id,result_data from result where account=#{account} and course_id =#{courseId}")
    TResult selectResData(Integer courseId,String account);
    /**
     * 根据课程id获取结果
     * @param courseId
     * @param type 区分是排课结果还是调查结果
     * @return
     */
    List<String> queryResultDataByCourseId(@Param("courseId") int courseId,@Param("type") char type);

    /**
     * 更新选课结果
     */
    @Update("update result set result_data=#{resultData} where r_id=#{rId}")
    int updateResDataById(TResult tResult);

    /**
     * 查询结果数据的主键
     */
    @Select("select r_id from result where account=#{account} and course_id =#{courseId}")
    int selectRidByAccountAndCourseId(TResult result);

    @Select("select r_id,course_id,account,result_data from result where course_id=#{courseId} and result_type= 'S'")
    List<TResult> selectResultListByCourseId(Integer courseId);


    /**
     * 删除指定排课
     * @param courseId
     * @param type
     */
    @Delete("delete from result where course_id=#{courseId} and result_type=#{type}")
    void deleteResult(@Param("courseId") int courseId,@Param("type") String type);
}
