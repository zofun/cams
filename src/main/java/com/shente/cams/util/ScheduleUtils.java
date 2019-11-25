package com.shente.cams.util;

import com.shente.cams.pojo.PlanOfOneWeek;

/**
 * @author TCW
 * 用于排每一周的课程的工具类
 */
public class ScheduleUtils {

    /**
     * 排特定一周的课程
     * @param week 周次数
     * @param arr 学生空闲时间数组
     * @param times 本周预计学时
     * @param stuNum 学生统计信息样本数
     * @param flag 是否启用最少2节课连续的限制
     * @return
     */
    public static PlanOfOneWeek ScheduleOneWeek(int week, byte[][] arr, int times, int stuNum,boolean flag) {

        /*---给当前周进行排课---*/
        PlanOfOneWeek plan = new PlanOfOneWeek(week);
        //每周最大应排课时

        for (int day = 0; day < 7; day++) {
            /*---排上午的---*/
            int b = 0;
            int e = 0;
            while (b < 5 && arr[day][b] != stuNum) {
                b++;
            }
            e = b;
            while (e < 5 && arr[day][e] == stuNum) {
                e++;
            }
            //至少能排两节课,或忽略最少连拍限制
            if ((e - b >= 2)||flag) {
                for (int i = b; i < e && times > 0; i++) {
                    plan.setStudytime(day + 1, i + 1);

                    times--; //应该排课的学时
                }
            }

            /*---排下午的---*/
            b = 5;
            while (b < 9 && arr[day][b] != stuNum) {
                b++;
            }
            e = b;
            while (e < 9 && arr[day][e] == stuNum) {
                e++;
            }
            //至少能排两节课,或忽略最少连拍限制
            if ((e - b >= 2)||flag) {
                for (int i = b; i < e && times > 0; i++) {
                    plan.setStudytime(day + 1, i + 1);
                    times--;

                }
            }

            /*---排晚上的的---*/
            b = 9;
            while (b < 12 && arr[day][b] != stuNum) {
                b++;
            }
            e = b;
            while (e < 12 && arr[day][e] == stuNum) {
                e++;
            }
            //至少能排两节课,或忽略最少连拍限制
            if ((e - b >= 2)||flag) {
                for (int i = b; i < e && times > 0; i++) {
                    plan.setStudytime(day + 1, i + 1);
                    times--;

                }
            }


        }
        return plan;
    }


}
