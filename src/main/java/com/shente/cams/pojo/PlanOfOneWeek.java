package com.shente.cams.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
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


    public static void main(String[] args) throws JsonProcessingException {
        PlanOfOneWeek plan=new PlanOfOneWeek(1);
        plan.setStudytime(1,1);
        plan.setStudytime(1,2);
        PlanOfOneWeek planB=new PlanOfOneWeek(2);
        planB.setStudytime(1,1);
        planB.setStudytime(1,2);
        List<PlanOfOneWeek> list=new LinkedList<>();
        list.add(plan);
        list.add(planB);
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(list));
    }
}

@Getter
@Setter
@AllArgsConstructor
class Item{
    private int jc;
    private int day;
}
