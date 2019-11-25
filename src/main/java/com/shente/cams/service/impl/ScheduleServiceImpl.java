package com.shente.cams.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shente.cams.mapper.CourseMapper;
import com.shente.cams.mapper.ResultMapper;
import com.shente.cams.pojo.Course;
import com.shente.cams.pojo.FreeTimeInfo;
import com.shente.cams.pojo.PlanOfOneWeek;
import com.shente.cams.pojo.TResult;
import com.shente.cams.service.ScheduleService;
import com.shente.cams.util.JsonParseUtils;
import com.shente.cams.util.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public String coursesArranging(Integer courseId) throws IOException {
        List<String> ScheduleResult = resultMapper.queryResultDataByCourseId(courseId, 'T');
        if (!ScheduleResult.isEmpty()) {
            //已经有结果就直接使用
            return ScheduleResult.get(0);
        }


        final ObjectMapper mapper = new ObjectMapper();

        Course course = courseMapper.queryCourseById(courseId);
        //获取总学时
        int times = course.getHours();
        //获取行课周信息
        String weeksjson = course.getWeeks();
        List<String> weeks = mapper.readValue(weeksjson, new TypeReference<List<String>>() {
        });
        //每周的排课量
        int timesPreWeek = times / weeks.size();
        if(timesPreWeek==1){
            //一周至少应该有两节课
            timesPreWeek=2;
        }

        //获取学生的统计信息
        List<String> list = resultMapper.queryResultDataByCourseId(courseId, 'S');

        //每位学生的空闲时间信息
        List<FreeTimeInfo> infos = new ArrayList<>(list.size());
        //用于记录每周的排课结果
        List<PlanOfOneWeek> result = new LinkedList<>();

        for (String str : list) {
            //解析json数据到对象
            FreeTimeInfo info = JsonParseUtils.ParseJson(str);
            infos.add(info);
        }


        for (String week : weeks) {
            //按周统计 一周七天，每天12小节课
            byte[][] arr = new byte[7][12];
            //参与该周统计的学生的数量
            int stuNum = 0;
            //一周的一周的统计，一周一周的排课
            for (FreeTimeInfo info : infos) {
                if (info.map.containsKey(week)) {
                    stuNum++;
                    //如果此学生含有此周的空闲时间情况就进行统计，前端应该进行校验，确保它每周都统计了的
                    List<String> freeTime = (List<String>) info.map.get(week);
                    Iterator<String> iterator = freeTime.iterator();
                    while (iterator.hasNext()) {
                        String s = iterator.next();
                        String[] strs = s.split(":");
                        arr[Integer.parseInt(strs[0]) - 1][Integer.parseInt(strs[1]) - 1]++;

                    }
                }

            }
            if (stuNum == 0) {
                //当前周缺失学生统计，无法进行排课
                continue;
            }
            if(times==0){
                break;
            }
            PlanOfOneWeek plan;
            if(times<2){
                //剩下一节的情况
                plan = ScheduleUtils.ScheduleOneWeek(Integer.parseInt(week), arr, times, stuNum,true);
                times -=plan.getSize();
            }else{
                plan = ScheduleUtils.ScheduleOneWeek(Integer.parseInt(week), arr, timesPreWeek, stuNum,false);
                times -= plan.getSize();
            }

            result.add(plan);
            plan=null;
        }
        if (times != 0) {
            throw new RuntimeException("该课程排不满");
        }

        String resultOfJson = mapper.writeValueAsString(result);
        //将结果持久化到数据库
        resultMapper.insert(new TResult(null, courseId, "result", resultOfJson, 'T'));
        return resultOfJson;
    }


    @Override
    public int addStuFreeTimeInfo(String account, Integer courseId, String json) {
        TResult r = new TResult(null, courseId, account, json, 'S');
        resultMapper.insert(r);
        return r.getRId();
    }


}

