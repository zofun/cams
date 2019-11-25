package com.shente.cams.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shente.cams.pojo.FreeTimeInfo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JsonParseUtils {

    /**
     * 将学生的空闲信息json转化为对象
     *
     * @return
     */
    public static FreeTimeInfo ParseJson(String json) {

        ObjectMapper mapper = new ObjectMapper();
        //存储一位学生空闲信息
        FreeTimeInfo info = new FreeTimeInfo();
        try {
            JsonNode node = mapper.readTree(json);
            for (int i = 0; i < node.size(); i++) {
                JsonNode childNode = node.get(i);

                JsonNode week = childNode.get("week");
                //weekDays是数组
                JsonNode weekDays = childNode.get("weekDays");
                //记录一周的空闲情况
                List<String> freeTimeOfOneWeek = new LinkedList<>();
                for (int j = 0; j < weekDays.size(); j++) {
                    JsonNode jcOfDay = weekDays.get(j);
                    freeTimeOfOneWeek.add(jcOfDay.get("day").asText() + ":" + jcOfDay.get("jc").asText());
                }
                info.map.put(week.toString(), freeTimeOfOneWeek);
            }
            return info;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}



