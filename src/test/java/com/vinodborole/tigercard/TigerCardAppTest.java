package com.vinodborole.tigercard;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.util.FareUtil;
import com.vinodborole.tigercard.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import java.io.InputStream;
import java.util.*;
public class TigerCardAppTest {

    @Test
    public void totalApplicableFareForSingleDayJourney_test(){
        try {
            List<Journey> journeys = getSingleDayJourney();
            double totalApplicableFare = FareUtil.calculateTotalApplicableFare(journeys);
            Assert.assertEquals(120.0,totalApplicableFare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void totalApplicableFareForSingleWeekJourney_test(){
        try {
            List<Journey> journeys = getSingleWeekJourney();
            double totalApplicableFare = FareUtil.calculateTotalApplicableFare(journeys);
            Assert.assertEquals(655.0,totalApplicableFare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void totalApplicableFareForMultipleWeekJourney_test(){
        try {
            List<Journey> journeys = getMultipleWeekJourney();
            double totalApplicableFare = FareUtil.calculateTotalApplicableFare(journeys);
            Assert.assertEquals(820.0,totalApplicableFare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    private List<Journey> getSingleDayJourney() throws Exception {
        InputStream inputStream = TigerCardAppTest.class.getResourceAsStream("/single-day.txt");
        List<Journey> journeys = FileUtil.getJourney(inputStream);
        return journeys;
    }
    private List<Journey> getSingleWeekJourney() throws Exception {
        InputStream inputStream = TigerCardAppTest.class.getResourceAsStream("/single-week.txt");
        List<Journey> journeys = FileUtil.getJourney(inputStream);
        return journeys;
    }
    private List<Journey> getMultipleWeekJourney() throws Exception {
        InputStream inputStream = TigerCardAppTest.class.getResourceAsStream("/week-overflow.txt");
        List<Journey> journeys = FileUtil.getJourney(inputStream);
        return journeys;
    }
}
