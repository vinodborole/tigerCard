package com.vinodborole.tigercard;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FareUtil {

    public static final int ZONE_1_1_PEAK = 30;
    public static final int ZONE_2_2_PEAK = 25;
    public static final int ZONE_1_2_1_PEAK = 35;

    public static final int ZONE_1_1_OFF_PEAK = 25;
    public static final int ZONE_2_2_OFF_PEAK = 20;
    public static final int ZONE_1_2_1_OFF_PEAK = 30;

    public static final int DAILY_CAP_ZONE_1_1 = 100;
    public static final int DAILY_CAP_ZONE_1_2_1 = 120;
    public static final int DAILY_CAP_ZONE_2_2 = 80;

    public static final int WEEKLY_CAP_ZONE_1_1 = 500;
    public static final int WEEKLY_CAP_ZONE_1_2_1 = 600;
    public static final int WEEKLY_CAP_ZONE_2_2 = 400;

    public static LocalTime PEAK_TIME_MON_SAT_START_1 = LocalTime.parse("07:00:00");
    public static LocalTime PEAK_TIME_MON_SAT_END_1 = LocalTime.parse("10:30:00");

    public static LocalTime PEAK_TIME_MON_SAT_START_2 = LocalTime.parse("17:00:00");
    public static LocalTime PEAK_TIME_MON_SAT_END_2 = LocalTime.parse("20:00:00");

    public static LocalTime PEAK_TIME_SAT_SUN_START_1 = LocalTime.parse("09:00:00");
    public static LocalTime PEAK_TIME_SAT_SUN_END_1 = LocalTime.parse("11:00:00");

    public static LocalTime PEAK_TIME_SAT_SUN_START_2 = LocalTime.parse("18:00:00");
    public static LocalTime PEAK_TIME_SAT_SUN_END_2 = LocalTime.parse("22:00:00");

    public static int calculateFare(Date date, int fromZone, int toZone){
        LocalTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day >= Calendar.MONDAY && day <= Calendar.SATURDAY){
            if (time.isAfter(PEAK_TIME_MON_SAT_START_1) && time.isBefore(PEAK_TIME_MON_SAT_END_1) ||
                    time.isAfter(PEAK_TIME_MON_SAT_START_2) && time.isBefore(PEAK_TIME_MON_SAT_END_2)){
                return getPeakHourFare(fromZone, toZone);
            }else{
                return getOffPeakHourFare(fromZone, toZone);
            }
        }else{
            if (time.isAfter(PEAK_TIME_SAT_SUN_START_1) && time.isBefore(PEAK_TIME_SAT_SUN_END_1) ||
                    time.isAfter(PEAK_TIME_SAT_SUN_START_2) && time.isBefore(PEAK_TIME_SAT_SUN_END_2)){
                return getPeakHourFare(fromZone, toZone);
            }else{
                return getOffPeakHourFare(fromZone, toZone);
            }
        }
    }

    private static int getOffPeakHourFare(int fromZone, int toZone) {
        if (fromZone == 1 && toZone == 1) {
            return ZONE_1_1_OFF_PEAK;
        }else if (fromZone == 2 && toZone == 2){
            return ZONE_2_2_OFF_PEAK;
        }else if(fromZone == 1 && toZone == 2 || fromZone == 1 && toZone == 2){
            return ZONE_1_2_1_OFF_PEAK;
        }
        return 0;
    }

    private static int getPeakHourFare(int fromZone, int toZone) {
        if (fromZone == 1 && toZone == 1) {
            return ZONE_1_1_PEAK;
        }else if (fromZone == 2 && toZone == 2){
            return ZONE_2_2_PEAK;
        }else if(fromZone == 1 && toZone == 2 || fromZone == 1 && toZone == 2){
            return ZONE_1_2_1_PEAK;
        }
        return 0;
    }
    //get the maximum fair journey and compare its zones
    public static int getDailyCapForJourneys(List<Journey> journeys){
        Collections.sort(journeys,new JourneyFareComparator());
        if (journeys.get(0).getFromZone() == 1 && journeys.get(0).getToZone() == 2){
            return DAILY_CAP_ZONE_1_2_1;
        }else if (journeys.get(0).getFromZone() == 1 && journeys.get(0).getToZone() == 1){
            return DAILY_CAP_ZONE_1_1;
        }else if (journeys.get(0).getFromZone() == 2 && journeys.get(0).getToZone() == 2){
            return DAILY_CAP_ZONE_2_2;
        }
        return 0;
    }
}
