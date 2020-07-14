package me.groupFour.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
//Price entity is mapped from the database using hibernate price is determined for a flight based on the time of booking and ticketCode and classcode aswell as the flight number.
@Entity()
@Table(name="price")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceID;
    @JoinColumn(name="AirlineCode")
    @ManyToOne()
    private AirlineEntity AirlineCode;
    String FlightNumber;
    @ManyToOne()
    @JoinColumn(name="ClassCode")
    private ClassEntity ClassCode;
    @ManyToOne()
    @JoinColumn(name="TicketCode")
    private TicketEntity TicketCode;
    private Timestamp StartDate;
    private Timestamp EndDate;
    private BigDecimal Price;
    private BigDecimal PriceLeg1;
    private BigDecimal PriceLeg2;

    public PriceEntity(BigDecimal price) {
        Price = price;
    }

    public PriceEntity() {
    }



    public PriceEntity(int priceID, AirlineEntity airlineCode, String flightNumber, ClassEntity classCode, TicketEntity ticketCode, Timestamp startDate, Timestamp endDate, BigDecimal price, BigDecimal priceLeg1, BigDecimal priceLeg2) {
        this.priceID = priceID;
        AirlineCode = airlineCode;
        FlightNumber = flightNumber;
        ClassCode = classCode;
        TicketCode = ticketCode;
        StartDate = startDate;
        EndDate = endDate;
        Price = price;
        PriceLeg1 = priceLeg1;
        PriceLeg2 = priceLeg2;
    }

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public AirlineEntity getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(AirlineEntity airlineCode) {
        AirlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

    public ClassEntity getClassCode() {
        return ClassCode;
    }

    public void setClassCode(ClassEntity classCode) {
        ClassCode = classCode;
    }

    public TicketEntity getTicketCode() {
        return TicketCode;
    }

    public void setTicketCode(TicketEntity ticketCode) {
        TicketCode = ticketCode;
    }

    public Timestamp getStartDate() {
        return StartDate;
    }

    public void setStartDate(Timestamp startDate) {
        StartDate = startDate;
    }

    public Timestamp getEndDate() {
        return EndDate;
    }

    public void setEndDate(Timestamp endDate) {
        EndDate = endDate;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public BigDecimal getPriceLeg1() {
        return PriceLeg1;
    }

    public void setPriceLeg1(BigDecimal priceLeg1) {
        PriceLeg1 = priceLeg1;
    }

    public BigDecimal getPriceLeg2() {
        return PriceLeg2;
    }

    public void setPriceLeg2(BigDecimal priceLeg2) {
        PriceLeg2 = priceLeg2;
    }
}
