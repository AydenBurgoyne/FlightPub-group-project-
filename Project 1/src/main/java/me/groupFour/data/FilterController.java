/*
    Note: Collections using a Timsort to sort the list
 */
package me.groupFour.data;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//Used to filter the journeylist based on a given criteria.
public class FilterController {

    public static Integer PAGE_SIZE = 10;

    public List<JourneyEntity> sortByPrice(List<JourneyEntity> dummyJourneys,boolean ascending){
        if(ascending){
            Collections.sort(dummyJourneys, (a, b) -> a.getPrice().doubleValue() > b.getPrice().doubleValue() ? -1 : a.getPrice() == b.getPrice() ? 0 : 1);
        }else {
            Collections.sort(dummyJourneys, (a, b) -> a.getPrice().doubleValue() < b.getPrice().doubleValue() ? -1 : a.getPrice() == b.getPrice() ? 0 : 1);
        }

        return dummyJourneys;
    }

    public List<JourneyEntity> filterByPrice(List<JourneyEntity> dummyJourneys,Double lowestPrice,Double highestPrice){
        List<JourneyEntity> filteredList = new LinkedList<>();

        for(JourneyEntity journey : dummyJourneys){
            if(journey.getPrice().doubleValue() > lowestPrice || journey.getPrice().doubleValue() < highestPrice){
                filteredList.add(journey);
            }
        }

        return filteredList;
    }

    public List<JourneyEntity> filterByNoOfStops(List<JourneyEntity> dummyJourneys,int maxNoOfStops){
        List<JourneyEntity> filteredList = new LinkedList<>();

        for(JourneyEntity journey : dummyJourneys){
            if(journey.getLegsOfJourney().size() <= maxNoOfStops){
                filteredList.add(journey);
            }
        }

        return filteredList;
    }


    //maxDuration measure in hours
    public List<JourneyEntity> filterByDurationTime(List<JourneyEntity> dummyJourneys,int maxDuration){

        List<JourneyEntity> filteredList = new LinkedList<>();

        for(JourneyEntity journey : dummyJourneys){
            if(journey.getTotalDuration() <= maxDuration){
                filteredList.add(journey);
            }
        }

        return filteredList;
    }

    public List<JourneyEntity> getPage(List<JourneyEntity> journey, int pageNumber){
        int sum = pageNumber * PAGE_SIZE;

        if(sum > journey.size()){
            return journey.subList(journey.size()-PAGE_SIZE,journey.size());
        }

        int end = sum + PAGE_SIZE;

        if(end > journey.size()){
            end = journey.size();
        }

        return journey.subList(sum,sum+PAGE_SIZE);
    }
}
