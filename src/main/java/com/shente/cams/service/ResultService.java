package com.shente.cams.service;

import com.shente.cams.pojo.TResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sugar
 * 2019/11/22
 * 上午8:22
 * 类作用描述
 */
public interface ResultService {

    int addResult(TResult tResult);

    int updateResult(TResult tResult);

    TResult getResultByAccount(Integer courseId,String account);

    List<TResult> getPeopleData(Integer courseId);
}
