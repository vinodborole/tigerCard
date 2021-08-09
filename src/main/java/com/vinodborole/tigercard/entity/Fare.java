package com.vinodborole.tigercard.entity;

import java.util.HashMap;
import java.util.Map;

public class Fare {
    static Map<String, Double> farePeakHours = new HashMap<>();
    static Map<String, Double> fareOffHours = new HashMap<>();

    static {
        //peak hours
        farePeakHours.put("1-1", 30.0);
        farePeakHours.put("1-2", 35.0);
        farePeakHours.put("2-1", 35.0);
        farePeakHours.put("2-2", 25.0);

        //off hours
        fareOffHours.put("1-1", 25.0);
        fareOffHours.put("1-2", 30.0);
        fareOffHours.put("2-1", 30.0);
        fareOffHours.put("2-2", 20.0);
    }

    public static double getPeakHourFare(int fromZone, int toZone) {
        String key = fromZone+ "-" +toZone;
        return farePeakHours.getOrDefault(key, 0.0);
    }

    public static double getOffHourFare(int fromZone, int toZone) {
        String key = fromZone+ "-" +toZone;
        return fareOffHours.getOrDefault(key, 0.0);
    }
}
