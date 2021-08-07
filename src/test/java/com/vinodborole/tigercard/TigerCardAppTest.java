package com.vinodborole.tigercard;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TigerCardAppTest {

    @Test
    public void testDailyCap(){
        try {
            List<Journey> journeys = getSingleDayJourney();
            int dailyCap = FareUtil.getDailyCapForJourneys(journeys);
            Assert.assertEquals(120,dailyCap);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    private List<Journey> getSingleDayJourney() throws Exception {
        InputStream inputStream = TigerCardAppTest.class.getResourceAsStream("/single-day.txt");
        List<Journey> journeys = TigerCardUtil.getJourney(inputStream);
        return journeys;
    }
    private List<Journey> getSingleWeekJourney() throws Exception {
        InputStream inputStream = TigerCardAppTest.class.getResourceAsStream("/single-week.txt");
        List<Journey> journeys = TigerCardUtil.getJourney(inputStream);
        return journeys;
    }
    private List<Journey> getWeekOverFlowJourney() throws Exception {
        InputStream inputStream = TigerCardAppTest.class.getResourceAsStream("/week-overflow.txt");
        List<Journey> journeys = TigerCardUtil.getJourney(inputStream);
        return journeys;
    }
}
