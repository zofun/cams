package com.shente.cams.service.impl;

import com.shente.cams.mapper.ResultMapper;
import com.shente.cams.pojo.TResult;
import com.shente.cams.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sugar
 * 2019/11/22
 * 上午8:26
 * 类作用描述
 */

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultMapper resultMapper;


    @Override
    public int addResult(TResult tResult) {
//        插入
        resultMapper.insert(tResult);
        return  resultMapper.selectRidByAccountAndCourseId(tResult);
    }

    /**
     * 更新空闲数据
     */
    @Override
    public int updateResult(TResult tResult) {
        return resultMapper.updateResDataById(tResult);
    }

    @Override
    public TResult getResultByAccount(Integer courseId,String account) {
        return resultMapper.selectResData(courseId, account);
    }

    @Override
    public List<TResult> getPeopleData(Integer courseId) {
        return resultMapper.selectResultListByCourseId(courseId);
    }
}
