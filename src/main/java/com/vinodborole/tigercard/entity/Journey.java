package com.vinodborole.tigercard.entity;

import com.vinodborole.tigercard.exception.FareException;
import com.vinodborole.tigercard.util.FareUtil;

import java.util.Calendar;
import java.util.Date;

public class Journey {
    private Date date;
    private int fromZone;
    private int toZone;

    private double fare;
    private int weekOfMonth;
    private int monthOfYear;
    private int dayOfWeek;

    public Journey(Date date, int fromZone, int toZone) {
        this.date = date;
        this.fromZone=fromZone;
        this.toZone=toZone;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
        monthOfYear = cal.get(Calendar.MONTH);
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    }
    public Date getDate(){return date;}
    public int getFromZone() {
        return fromZone;
    }
    public int getToZone() {
        return toZone;
    }
    public double getFare() throws FareException {
        return FareUtil.calculateJourneyFare(date,fromZone,toZone);
    }
    public int getWeekOfMonth() {
        return weekOfMonth;
    }
    public int getMonthOfYear() {
        return monthOfYear;
    }
    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
