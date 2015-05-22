package com.thoughtworks.cathywu.tools.fordate;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by lzwu on 2/6/15.
 */
public class DateCountCalculator {

    public static int countNumber(Date fromDate, Date toDate) {
        int dayNum = (int) ((toDate.getTime() - fromDate.getTime()) / 24 / 3600 / 1000);

        Calendar date = Calendar.getInstance(TimeZone.getDefault());
        int count = 0;
        for (int i = 0; i < dayNum; i++) {
            date.setTime(new Date(fromDate.getTime() + i * 24 * 3600 * 1000));
            if (date.get(Calendar.DAY_OF_WEEK) == 1 || date.get(Calendar.DAY_OF_WEEK) == 7) {
                count++;
            }
        }
        System.out.println(dayNum);
        return count;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(2015, 1, 2, 0, 0, 0);
        Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
        calendar2.set(2015, 6, 1, 0, 0, 0);
        System.out.println(countNumber(calendar.getTime(), calendar2.getTime()));
    }
}
