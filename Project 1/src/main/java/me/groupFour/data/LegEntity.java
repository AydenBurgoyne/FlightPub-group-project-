package me.groupFour.data;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
//Leg Entity is the objeect that stores the flight for each part of the journey. It is stored in the database when a booking is made.
@Entity()
@Table(name="Leg")
public class LegEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer LegID;
    @ManyToOne()
    @JoinColumn(name="flightID")
    private FlightEntity flightID;
    private String seat;
    @ManyToOne()
    @JoinColumn(name="JourneyID")
    private BookedJourneyEntity JourneyID;
    private Timestamp bookedTime; //time that the leg is booked.
    @JoinColumn(name="TicketCode")
    @ManyToOne()
    private TicketEntity tickettype;
    @JoinColumn(name="ClassCode")
    @ManyToOne()
    private ClassEntity ticketclass;
    @ManyToMany()
    @JoinTable(
            name="Price_Leg_Relation",
            joinColumns= @JoinColumn(name="LegID"),
            inverseJoinColumns = @JoinColumn(name="flightID")
    )
    private List<PriceEntity> price;
    @Transient
    private PriceEntity priceEn;
    public void setPriceEntity(){
        Iterator<PriceEntity> it = price.iterator();
        while(it.hasNext()){
            PriceEntity temp = it.next();
            Timestamp start = temp.getStartDate();
            Timestamp end = temp.getEndDate();
            if(temp.getClassCode().getClassCode().equals(ticketclass.getClassCode())&&temp.getTicketCode().getTicketCode().equals(tickettype.getTicketCode())&&bookedTime.after(start)&&bookedTime.before(end)){

                priceEn = temp;
                return;
            }
        }
    }

    public LegEntity(Integer legID, FlightEntity flightID, String seat, Timestamp bookedTime, TicketEntity tickettype, ClassEntity ticketclass, List<PriceEntity> price) {
        LegID = legID;
        this.flightID = flightID;
        this.seat = seat;
        this.bookedTime = bookedTime;
        this.tickettype = tickettype;
        this.ticketclass = ticketclass;
        this.price = price;
        setPriceEntity();

    }

    public LegEntity() {
    }

    public Integer getLegID() {
        return LegID;
    }

    public void setLegID(Integer legID) {
        LegID = legID;
    }

    public FlightEntity getFlightID() {
        return flightID;
    }

    public void setFlightID(FlightEntity flightID) {
        this.flightID = flightID;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BookedJourneyEntity getJourneyID() {
        return JourneyID;
    }

    public void setJourneyID(BookedJourneyEntity journeyID) {
        JourneyID = journeyID;
    }

    public Timestamp getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(Timestamp bookedTime) {
        this.bookedTime = bookedTime;
    }

    public TicketEntity getTickettype() {
        return tickettype;
    }

    public void setTickettype(TicketEntity tickettype) {
        this.tickettype = tickettype;
    }

    public ClassEntity getTicketclass() {
        return ticketclass;
    }

    public void setTicketclass(ClassEntity ticketclass) {
        this.ticketclass = ticketclass;
    }

    public List<PriceEntity> getPrice() {
        return price;
    }

    public void setPrice(List<PriceEntity> price) {
        this.price = price;
    }

    public PriceEntity getPriceEn() {
        return priceEn;
    }

    public void setPriceEn(PriceEntity priceEn) {
        this.priceEn = priceEn;
    }
}
