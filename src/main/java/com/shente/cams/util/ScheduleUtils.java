package com.shente.cams.util;

import com.shente.cams.pojo.PlanOfOneWeek;

public class ScheduleUtils {

    public static PlanOfOneWeek ScheduleOneWeek(int week, byte[][] arr, int times, int stuNum) {

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
            //至少能排两节课
            if (e - b >= 2) {
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
            //至少能排两节课
            if (e - b >= 2) {
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
            //至少能排两节课
            if (e - b >= 2) {
                for (int i = b; i < e && times > 0; i++) {
                    plan.setStudytime(day + 1, i + 1);
                    times--;

                }
            }


        }
        return plan;
    }


}
