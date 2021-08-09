package com.vinodborole.tigercard.entity;

import java.util.HashMap;
import java.util.Map;

public class ZoneFare {
    static Map<String, Double> zoneFarePeakHours = new HashMap<>();
    static Map<String, Double> zoneFareOffHours = new HashMap<>();

    static {
        //peak hours
        zoneFarePeakHours.put("1-1", 30.0);
        zoneFarePeakHours.put("1-2", 35.0);
        zoneFarePeakHours.put("2-1", 35.0);
        zoneFarePeakHours.put("2-2", 25.0);

        //off hours
        zoneFareOffHours.put("1-1", 25.0);
        zoneFareOffHours.put("1-2", 30.0);
        zoneFareOffHours.put("2-1", 30.0);
        zoneFareOffHours.put("2-2", 20.0);
    }

    public double getPeakHourFare(int fromZone, int toZone) {
        String key = fromZone+ "-" +toZone;
        return zoneFarePeakHours.getOrDefault(key, 0.0);
    }

    public double getOffHourFare(int fromZone, int toZone) {
        String key = fromZone+ "-" +toZone;
        return zoneFareOffHours.getOrDefault(key, 0.0);
    }
}
