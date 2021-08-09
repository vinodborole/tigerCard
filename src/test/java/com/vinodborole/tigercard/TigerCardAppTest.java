package com.vinodborole.tigercard;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.exception.FareException;
import com.vinodborole.tigercard.util.FareUtil;
import com.vinodborole.tigercard.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
public class TigerCardAppTest {

    @Test(expected = FareException.class)
    public void calculateJourneyFare_Invalid_From_Zone_test() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date peakHour = formatter.parse("02/08/2021 10:20:33");
        double fare = FareUtil.calculateJourneyFare(peakHour,4,1);
    }

    @Test(expected = FareException.class)
    public void calculateJourneyFare_Invalid_To_Zone_test() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date peakHour = formatter.parse("02/08/2021 10:20:33");
        double fare = FareUtil.calculateJourneyFare(peakHour,2,4);
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_One_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,1);
            Assert.assertEquals(30.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_Two_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,2);
            Assert.assertEquals(25.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_Two_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,2);
            Assert.assertEquals(35.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_One_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,1);
            Assert.assertEquals(35.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_One_Off_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,1);
            Assert.assertEquals(25.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_Two_Off_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,2);
            Assert.assertEquals(20.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_Two_Off_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,2);
            Assert.assertEquals(30.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_One_Off_Peak_Hours_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("02/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,1);
            Assert.assertEquals(30.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_One_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,1);
            Assert.assertEquals(30.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_Two_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,2);
            Assert.assertEquals(25.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_Two_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,2);
            Assert.assertEquals(35.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_One_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 10:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,1);
            Assert.assertEquals(35.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_One_Off_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,1);
            Assert.assertEquals(25.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_Two_Off_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,2);
            Assert.assertEquals(20.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_One_To_Two_Off_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,1,2);
            Assert.assertEquals(30.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateJourneyFare_Zone_Two_To_One_Off_Peak_Hours_Weekend_test(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date peakHour = formatter.parse("07/08/2021 11:20:33");
            double fare = FareUtil.calculateJourneyFare(peakHour,2,1);
            Assert.assertEquals(30.0,fare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateTotalApplicableFare_For_Single_Day_Journey_test(){
        try {
            List<Journey> journeys = getSingleDayJourney();
            double totalApplicableFare = FareUtil.calculateTotalApplicableFare(journeys);
            Assert.assertEquals(120.0,totalApplicableFare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateTotalApplicableFare_For_Single_Week_Journey_test(){
        try {
            List<Journey> journeys = getSingleWeekJourney();
            double totalApplicableFare = FareUtil.calculateTotalApplicableFare(journeys);
            Assert.assertEquals(655.0,totalApplicableFare,0.0);
        }catch(Exception e){
            Assert.fail("Should not have thrown any exception");
        }
    }

    @Test
    public void calculateTotalApplicableFare_For_Multiple_Week_Journey_test(){
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
