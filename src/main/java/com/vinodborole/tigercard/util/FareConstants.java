package com.vinodborole.tigercard.util;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class FareConstants {
    private FareConstants(){}

    public static final int MINIMUM_NUMBER_OF_JOURNEY_APPLICABLE_FOR_DAILY_PASS = 3;

    public static final List<Double> CAP_ZONE_1_1 = Arrays.asList(100.0,500.0);
    public static final List<Double> CAP_ZONE_1_2_1 = Arrays.asList(120.0,600.0);
    public static final List<Double> CAP_ZONE_2_2 = Arrays.asList(80.0,400.0);

    public static final LocalTime PEAK_TIME_MON_SAT_START_1 = LocalTime.parse("07:00:00");
    public static final LocalTime PEAK_TIME_MON_SAT_END_1 = LocalTime.parse("10:30:00");

    public static final LocalTime PEAK_TIME_MON_SAT_START_2 = LocalTime.parse("17:00:00");
    public static final LocalTime PEAK_TIME_MON_SAT_END_2 = LocalTime.parse("20:00:00");

    public static final LocalTime PEAK_TIME_SAT_SUN_START_1 = LocalTime.parse("09:00:00");
    public static final LocalTime PEAK_TIME_SAT_SUN_END_1 = LocalTime.parse("11:00:00");

    public static final LocalTime PEAK_TIME_SAT_SUN_START_2 = LocalTime.parse("18:00:00");
    public static final LocalTime PEAK_TIME_SAT_SUN_END_2 = LocalTime.parse("22:00:00");
}
