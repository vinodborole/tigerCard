package com.vinodborole.tigercard.util;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.entity.Fare;
import com.vinodborole.tigercard.entity.Zones;
import com.vinodborole.tigercard.exception.FareException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import static com.vinodborole.tigercard.util.FareConstants.*;

public class FareUtil {
    /**
     * Returns the Journey Fare for a specific date and from source zone to destination zone .
     *
     * This method calculates the Journey Fare by checking the zone and date if its a weekday or weekend and if the timings are
     * within peak hour or off peak hours.
     *
     * @param  Date  Journey date and time
     * @param  fromZone  Journey source zone
     * @param  toZone  Journey destination zone
     * @return              Cost of the journey
     * @throws Throws a FareException in case the zones are invalid.
     */
    public static Double calculateJourneyFare(Date date, int fromZone, int toZone) throws FareException {
        validateZones(fromZone,toZone);
        LocalTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day >= Calendar.MONDAY && day <= Calendar.SATURDAY){
            if (time.isAfter(PEAK_TIME_MON_SAT_START_1) && time.isBefore(PEAK_TIME_MON_SAT_END_1) ||
                    time.isAfter(PEAK_TIME_MON_SAT_START_2) && time.isBefore(PEAK_TIME_MON_SAT_END_2)){
                return Fare.getPeakHourFare(fromZone, toZone);
            }else{
                return Fare.getOffHourFare(fromZone, toZone);
            }
        }else{
            if (time.isAfter(PEAK_TIME_SAT_SUN_START_1) && time.isBefore(PEAK_TIME_SAT_SUN_END_1) ||
                    time.isAfter(PEAK_TIME_SAT_SUN_START_2) && time.isBefore(PEAK_TIME_SAT_SUN_END_2)){
                return Fare.getPeakHourFare(fromZone, toZone);
            }else{
                return Fare.getOffHourFare(fromZone, toZone);
            }
        }
    }

    private static void validateZones(int fromZone, int toZone) throws FareException {
       if(!(Zones.isValidZone(fromZone) && Zones.isValidZone(toZone))){
          throw new FareException("Invalid zones");
       }
    }

    /**
     * Returns the total applicable fare for the list of journeys.
     *
     * This method calculates total applicable fare for the list of journeys by grouping then by week, by day
     * and applying weekly and daily cap on the journeys if the conditions meet.
     *
     * @param  journey  List of Journey
     * @return              Total applicable fare for the list of journey
     * @throws Throws a FareException in case the zones are invalid.
     */
    public static double calculateTotalApplicableFare(List<Journey> journey) throws FareException{
        double totalApplicableFare = 0.0;
        Map<Integer,List<Journey>> groupByWeek = journey.stream()
                .collect(Collectors.groupingBy(Journey::getWeekOfMonth));
        Map<Integer,Double> weeklyFare = new HashMap<>();

        groupByWeek.forEach((week,wJourney)->{
            Map<Integer,List<Journey>> groupByDay = wJourney.stream()
                    .collect(Collectors.groupingBy(Journey::getDayOfWeek));
            Map<Integer,Double> dayFare = new HashMap<>();
            groupByDay.forEach((day,dJourney)->{
                List<Double> fareCap = FareUtil.getDailyAndWeeklyFareCapForJourneys(dJourney);
                if(fareCap!=null) {
                    Double weekCap = fareCap.get(1);
                    Double dayCap = fareCap.get(0);
                    dayFare.put(day,dayCap); //reset total day fair to day cap
                    if (dayFare.size()>1) { //apply weekly cap only when number of days are greater than one
                        weeklyFare.put(week, weekCap); //reset week fair to week cap
                    }else{
                        weeklyFare.put(week, getTotalDaysFair(dayFare));
                    }
                }else{
                    double totalDayFair = dJourney.stream()
                            .mapToDouble(Journey::getFare).sum();
                    dayFare.put(day,totalDayFair);
                    weeklyFare.put(week, getTotalDaysFair(dayFare));
                }
            });
        });
        System.out.println("**** TOTAL APPLICABLE FAIR ***");
        for (Map.Entry<Integer, Double> entry : weeklyFare.entrySet()) {
            System.out.printf("Week : %d , Total Fare: %f",entry.getKey(),entry.getValue());
            System.out.println();
            totalApplicableFare += entry.getValue();
        }
        return totalApplicableFare;
    }
    private static double getTotalDaysFair(Map<Integer,Double> dayFair){
        double totalDaysFair = 0.0;
        for (Map.Entry<Integer, Double> entry : dayFair.entrySet()) {
            totalDaysFair += entry.getValue();
        }
        return totalDaysFair;
    }

    /**
     * Returns weekly and daily cap for the list of journeys.
     *
     * This method gets the maximum cost of a journey and based on the zones it returns the daily and weekly cap.
     *
     * @param  journey  List of Journey
     * @return              Daily and weekly cap fare
     * @throws Throws a FareException in case the zones are invalid.
     */
    public static List<Double> getDailyAndWeeklyFareCapForJourneys(List<Journey> journeys) throws FareException{
        if (journeys.size() >= MINIMUM_NUMBER_OF_JOURNEY_APPLICABLE_FOR_DAILY_PASS) {
            Journey journey = journeys.stream()
                    .max(Comparator.comparing(Journey::getFare)).get();
            if (journey.getFromZone() == 1 && journey.getToZone() == 2 || journey.getFromZone() == 2 && journey.getToZone() == 1) {
                return CAP_ZONE_1_2_1;
            } else if (journey.getFromZone() == 1 && journey.getToZone() == 1) {
                return CAP_ZONE_1_1;
            } else if (journey.getFromZone() == 2 && journey.getToZone() == 2) {
                return CAP_ZONE_2_2;
            }
        }
        return null;
    }
}
