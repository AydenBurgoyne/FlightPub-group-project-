package me.groupFour.data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
//Journey entity is the equivelent of Booked Journey but differs in the fact it's not stored in the database. Once a customer books a journey then the information stored in this entity will be transfered over to a booked journey bean to be stored in the database.
    //non-persistent journey entity
public class JourneyEntity implements Cloneable{
    private Timestamp searchTime;
    private LinkedList<LegEntity> legsOfJourney= new LinkedList<LegEntity>();
    private BigDecimal price; //probs just caculate this in the constructor by adding all the prices from the legs.
    private Integer totalDuration=0;

    public JourneyEntity Clone() throws CloneNotSupportedException{
        JourneyEntity temp = (JourneyEntity)super.clone();
         temp.setLegsOfJourney((LinkedList<LegEntity>)temp.getLegsOfJourney().clone());
         return temp;
    }


    public void addLeg(LegEntity newLeg){
        legsOfJourney.add(newLeg);
    }

    public LinkedList<LegEntity> getLegsOfJourney() {
        return legsOfJourney;
    }

//calculating price is used to calculate the total price by adding all the prices from the legs up.
    public void calculatingPrice() {
        Iterator<LegEntity> it = legsOfJourney.iterator();
        price = new BigDecimal(0);
        while (it.hasNext()) {
            price = price.add(it.next().getPriceEn().getPrice());
        }
    }
    //calculates the total duration by adding all the leg durations from the legs.
    public void sumDuration(){
        int sum = 0;
        for(LegEntity leg : legsOfJourney) {
            sum += leg.getFlightID().getDuration();
        }
        totalDuration=sum;
    }
//calculates jet lag based on total duration
    public Integer calculateJetLag(){
        sumDuration();

        int jetlag = 1;
        double totalD=this.getTotalDuration();
        if( totalD >= 3 && totalD <= 12){
            jetlag = 2;
        }else if(totalD > 12){
            jetlag = 3;
        }

        return jetlag;
    }


    public void setLegsOfJourney(LinkedList<LegEntity> legsOfJourney) {
            this.legsOfJourney = legsOfJourney;
        }


    public JourneyEntity(LinkedList<LegEntity> legsOfJourney) {
        this.legsOfJourney = legsOfJourney;
        calculatingPrice();
    }

    public JourneyEntity() {
        Date date = new Date();
        searchTime = new Timestamp(date.getTime());
    }

    public Timestamp getSearchTime() {
        return searchTime;
    }
    public void generateDuration()
    {
        for (LegEntity l: legsOfJourney) {
            // add time.
        }
    }

    public void setSearchTime(Timestamp searchTime) {
        this.searchTime = searchTime;
    }

    public BigDecimal getPrice() {
        return price;
    }
    //Makes a string to input into the url for booking.
    public String getFlightIDs(){
        String output="";
        for (LegEntity legEntity : legsOfJourney) {
            output =output+Integer.toString(legEntity.getFlightID().getFlightID());
            output = output+",";
        }
        output =output.substring(0,output.length()-1);
        output=output+"&TicketType=";
        output=output+legsOfJourney.getLast().getTickettype().getTicketCode();
        output=output+"&ClassCode=";
        output=output+legsOfJourney.getLast().getTicketclass().getClassCode();
       return output;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    //
    public Timestamp getDepartureTime() {
        return legsOfJourney.getFirst().getFlightID().getDepartureTime();
    }
    public Timestamp getArrivalTime() {
        return legsOfJourney.getLast().getFlightID().getArrivalTime();
    }

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "legsOfJourney=" + legsOfJourney +
                ", price=" + price +
                '}';

    }


    public Double getTotalDuration() {
        if(totalDuration==0) {sumDuration();}
        double td = totalDuration;
        td/=60;
        return td;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;

    }
}
