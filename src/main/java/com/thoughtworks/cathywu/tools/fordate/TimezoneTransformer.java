package com.thoughtworks.cathywu.tools.fordate;

import java.util.TimeZone;

/**
 * @author lzwu
 * @since 8/26/15
 */
public class TimezoneTransformer {
    public static void main(String[] args) {
        String[] timeZones = TimeZone.getAvailableIDs();
        for (String timeZone : timeZones) {
            System.out.println(timeZone);
        }
    }
}
