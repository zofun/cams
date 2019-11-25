package com.shente.cams.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author TCW
 * 特定一周的排课方案
 */
@Getter
@Setter
public class PlanOfOneWeek {


    private int week;
    private List<Item> weekDays;

    public PlanOfOneWeek(int week){
        this.week=week;
        weekDays=new LinkedList<Item>();
    }

    /**
     * 设置上课时间
     */
    public void setStudytime(int day,int jc){
        weekDays.add(new Item(jc,day));

    }

    /**
     * 获取当前周所排的课程节数
     * @return
     */
    @JsonIgnore
    public int getSize(){
        return weekDays.size();
    }
}


@Setter
@AllArgsConstructor
class Item{
    private int jc;
    private int day;

    /**
     * 因为前端的json串中值为string，
     * 这个地方转一下类型，确保生成的json格式一致
     * @return
     */
    public String getJc() {
        return String.valueOf(jc);
    }

    /**
     * 因为前端的json串中值为string，
     * 这个地方转一下类型，确保生成的json格式一致
     * @return
     */
    public String getDay() {
        return String.valueOf(day);
    }
}
