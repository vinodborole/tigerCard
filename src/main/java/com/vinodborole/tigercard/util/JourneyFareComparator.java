package com.vinodborole.tigercard.util;

import com.vinodborole.tigercard.entity.Journey;

import java.util.Comparator;

public class JourneyFareComparator implements Comparator<Journey> {
    @Override
    public int compare(Journey o1, Journey o2) {
        if (o1.getFare()== o2.getFare())
            return 0;
        else if (o1.getFare() < o2.getFare())
            return 1;
        else
            return -1;
    }
}
