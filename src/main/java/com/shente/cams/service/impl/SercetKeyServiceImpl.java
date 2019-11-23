package com.shente.cams.service.impl;

import com.shente.cams.mapper.SercetKeyMapper;
import com.shente.cams.pojo.SercetKey;
import com.shente.cams.service.SercetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sugar
 * 2019/11/22
 * 下午3:28
 * 类作用描述
 */

@Service
public class SercetKeyServiceImpl implements SercetService {

    @Autowired
    private SercetKeyMapper sercetKeyMapper;

    @Override
    public int addKey(SercetKey key) {
        return sercetKeyMapper.insert(key);
    }

    @Override
    public String checkKey(Integer courseId) {
        return sercetKeyMapper.selectTextByCourseId(courseId);
    }

    @Override
    public int updateKey(Integer courseId, String text) {
        return sercetKeyMapper.updateTextByCourseId(courseId,text);
    }

    @Override
    public Integer checkCourseId(String key) {
        return sercetKeyMapper.selectCourseIdByKey(key);
    }
}
