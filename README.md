
## Problem Statement				
The transport officials from the city of Nepu want to design a payment system for public metro transport. They have come up with the idea of a prepaid card - TigerCard - which is an NFC enabled card that is to be tapped at entry and exit points of the metro stations.				
To make the fares easier to understand for the commuters, the city has been divided into zones. Zone 1 is the central area and Zone 2 forms a concentric ring around Zone 1. Each metro station has been assigned to a zone. In the future, more zones will be added as the metro expands.				
The problem statement is to design the fare calculation engine for TigerCard. Below are the definitions and fare rules. 				

#### Note:		 	 	 						
Use of any frameworks like REST APIs, Spring Boot, Django, databases, command line input is unnecessary and will complicate the solution. It will also extend the time taken to solve the problem. 			
Rules				
Time of travel
The fare varies based on the time of the trip. There are two types of fares based on time of travel:			

● Peak hours timings
Monday - Friday 07:00 - 10:30, 17:00 - 20:00
Saturday - Sunday 09:00 - 11:00, 18:00 - 22:00					
● Off-peak hours timings - All hours except the above peak hours

The below table shows the fare that commuters will have to pay for a single journey from station A to station B. There is no concept of a round-trip journey. 

Zones 1-1, Peak hours - 30, Off-peak hours 25 

Zones 1-2-1, Peak hours - 35, Off-peak hours 30

Zones 2-2, Peak hours - 30, Off-peak hours 20


#### Fare Capping					
Fare capping works by rewarding commuters with free rides after they meet the fare equivalent of a daily, weekly, or monthly pass. With fare capping, social equity is achieved by removing upfront cost barriers associated with the recurrent passes. For example, a single ride costs 30 and the daily pass costs 90, the commuter earns a daily pass after the first 3 rides. For the rest of the day, all rides will be free for the commuter. Due to fare capping, the commuter does not have to invest 90 on the daily pass as they get the same features using a regular card.					

The following capping categories are available:

● Daily

● Weekly

##### Capping Limits:

Zones 1-1, Daily Cap - 100 ,Weekly Cap (Monday-Sunday) - 500

Zones 1-2-1, Daily Cap - 120 ,Weekly Cap (Monday-Sunday) - 600

Zones 1-1, Daily Cap - 80 ,Weekly Cap (Monday-Sunday) - 400


The cap that is applicable for a day is based on the farthest journey in a day. For example, if a few journeys are within zone 1 and a single journey is between zone 1 & 2, then the daily cap applicable will be the one for zone 1 - 2. The first example later in the document illustrates the same. The weekly cap uses the same logic as the daily cap when determining the zones applicable for the week.					
The weekly cap works in conjunction with the daily cap. So, when computing the weekly cap, each day fare might still be capped to that day’s daily cap. The second example later in the document illustrates the same.


##### Problem statement					
Given a list of journeys, the program should return the fare applicable.

###### Input Format:

List of journeys for a single commuter, fields include date-time, from-zone, to-zone

###### Output: 

Computed Fare for the input journeys data					
Below are some sample scenarios to consider and understand the problem further. The explanation column describes the applicable rule.

##### Examples
1. Daily cap reached				
   In this example, there are some trips between zone 1 and 2 in the day. So, the applicable daily cap is 120.
   
######  Input: 

DAY, TIME, FROM ZONE, TO ZONE, CALCULATED FAIR,  EXPLANATION

Monday, 10:20, 2, 1, 35, Peak hour single fare

Monday, 10:45, 1, 1, 25, Off-peak single fare

Monday, 16:15, 1, 1, 25, Off-peak single fare

Monday, 18:15, 1, 1, 30, Peak hour single fare

Monday, 19:00, 1, 2, 5, Daily cap reached 120 for zone 1-2. Charged 5 instead of 35


   
######  Output: 120



2. Weekly cap reached					
   Important Note: 
   For easier explanation, the daily journeys have been rolled-up to just show the total fare applicable for a day as this makes it easier to understand how the weekly fare capping will be applicable. In the actual implementation, the input will be a list of individual journeys as shown in the previous scenario.					
   In this example, there are some trips between zone 1 and 2 in the week. So, the applicable weekly cap is 600. For Monday - Thursday, the daily cap was reached and so the fare is capped at 120 for them.
   
######  Input:

DAY, ZONES, CALCULATED DAILY FARE, EXPLANATION 

Monday, 1-2, 120, Daily cap reached

Tuesday, 1-2, 120, Daily cap reached

Wednesday, 1-2, 120, Daily cap reached

Thursday, 1-2, 120, Daily cap reached

Friday, 1-1, 80, Daily cap not reached

Saturday, 1-2, 0, A weekly cap of 600 reached,

Sunday, 1-2, 100, Next week started  Monday (next week)


######  Output: 700

