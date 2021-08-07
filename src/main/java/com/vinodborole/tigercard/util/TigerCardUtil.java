package com.vinodborole.tigercard.util;

import com.vinodborole.tigercard.entity.Journey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TigerCardUtil {
    public static List<Journey> getJourney(InputStream inputStream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<Journey> journeys = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] split = line.split(" ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = formatter.parse(split[0] + " " + split[1]);
            Journey r = new Journey(date, Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            journeys.add(r);
            //System.out.println(r.getDate() + " " + r.getFromZone() + " " + r.getToZone() + " " + r.getFare());
        }
        return journeys;
    }
    //TODO - Need to correct this
    public static Map<Integer,List<Journey>> getJourneyByDay(List<Journey> journeys){
        return journeys.stream()
                .collect(Collectors.groupingBy((Journey d) -> getDayOfMonth(d.getDate())));
    }
    //TODO - Need to correct this
    public static Map<Integer,List<Journey>> getJourneyByWeek(List<Journey> journeys){
        return journeys.stream()
                .collect(Collectors.groupingBy((Journey d) -> getWeekOfYear(d.getDate())));
    }
    private static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    private static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
}
