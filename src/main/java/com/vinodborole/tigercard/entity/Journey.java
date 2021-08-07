package com.vinodborole.tigercard.entity;

import com.vinodborole.tigercard.util.FareUtil;
import java.util.Date;

public class Journey implements Comparable<Journey> {
    private Date date;
    private int fromZone;
    private int toZone;
    private int fare;
    public Journey(Date date, int fromZone, int toZone) {
        this.date = date;
        this.fromZone=fromZone;
        this.toZone=toZone;
        this.fare= FareUtil.calculateFare(date,fromZone,toZone);
    }
    public Date getDate() {
        return date;
    }
    public int getFromZone() {
        return fromZone;
    }
    public int getToZone() {
        return toZone;
    }
    public int getFare() {
        return fare;
    }
    @Override
    public int compareTo(Journey o) {
        return (this.getDate()).compareTo(o.getDate());
    }
}
