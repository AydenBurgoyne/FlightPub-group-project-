package me.groupFour.data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
@Entity
//Flight entity is used to map the flight table from the database using hibernate.
@Table(name="flights")
public class FlightEntity {
    @Id
    private Integer FlightID;
    @Basic(optional=false)
    private String FlightNumber;
    @Basic(optional=false)
    @JoinColumn(name="AirlineCode")
    @ManyToOne()
    private AirlineEntity AirlineCode;
    @Basic(optional=false)
    @JoinColumn(name="DepartureCode")
    @ManyToOne()
    private DestinationEntity DepartureCode;
    @Basic(optional=false)
    @JoinColumn(name="DestinationCode")
    @ManyToOne()
    private DestinationEntity DestinationCode;
    @JoinColumn(name="StopOverCode")
    @ManyToOne()
    private DestinationEntity StopOverCode;
    @Basic(optional=false)
    private Timestamp DepartureTime;
    private Timestamp ArrivalTimeStopOver;
    private Timestamp DepartureTimeStopOver;
    @Basic(optional=false)
    private Timestamp ArrivalTime;
    @Basic(optional=false)
    @JoinColumn(name="PlaneCode")
    @ManyToOne()
    private PlaneEntity PlaneCode;
    @Basic(optional=false)
    private Integer Duration;
    private Integer DurationSecondLeg;
    @OneToMany(mappedBy = "FlightNumber")
    private List<PriceEntity> PriceList;
    @OneToMany(mappedBy = "flightID")
    private List<LegEntity> LegEntityList;

//constructor:


    public FlightEntity(Integer flightID, String flightNumber, AirlineEntity airlineCode, DestinationEntity departureCode, DestinationEntity destinationCode, DestinationEntity stopOverCode, Timestamp departureTime, Timestamp arrivalTimeStopOver, Timestamp departureTimeStopOver, Timestamp arrivalTime, PlaneEntity planeCode, Integer duration, Integer durationSecondLeg, List<PriceEntity> priceList, List<LegEntity> legEntityList) {
        FlightID = flightID;
        FlightNumber = flightNumber;
        AirlineCode = airlineCode;
        DepartureCode = departureCode;
        DestinationCode = destinationCode;
        StopOverCode = stopOverCode;
        DepartureTime = departureTime;
        ArrivalTimeStopOver = arrivalTimeStopOver;
        DepartureTimeStopOver = departureTimeStopOver;
        ArrivalTime = arrivalTime;
        PlaneCode = planeCode;
        Duration = duration;
        DurationSecondLeg = durationSecondLeg;
        PriceList = priceList;
        LegEntityList = legEntityList;
    }


    public FlightEntity(DestinationEntity departureCode, DestinationEntity destinationCode, Integer duration){
        this.DepartureCode = departureCode;
        this.DestinationCode = destinationCode;
        this.Duration = duration;
    }

    public FlightEntity(DestinationEntity departureCode, DestinationEntity stopOverCode, DestinationEntity destinationCode, Integer duration, Integer durationSecondLeg){
        this.DepartureCode = departureCode;
        this.StopOverCode = stopOverCode;
        this.DestinationCode = destinationCode;
        this.Duration = duration;
        this.DurationSecondLeg = durationSecondLeg;
    }


    public FlightEntity() {
    }

    public Integer getFlightID() {
        return FlightID;
    }

    public void setFlightID(Integer flightID) {
        FlightID = flightID;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

    public AirlineEntity getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(AirlineEntity airlineCode) {
        AirlineCode = airlineCode;
    }

    public DestinationEntity getDepartureCode() {
        return DepartureCode;
    }

    public void setDepartureCode(DestinationEntity departureCode) {
        DepartureCode = departureCode;
    }

    public DestinationEntity getDestinationCode() {
        return DestinationCode;
    }

    public void setDestinationCode(DestinationEntity destinationCode) {
        DestinationCode = destinationCode;
    }

    public DestinationEntity getStopOverCode() {
        return StopOverCode;
    }

    public void setStopOverCode(DestinationEntity stopOverCode) {
        StopOverCode = stopOverCode;
    }

    public Timestamp getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        DepartureTime = departureTime;
    }

    public Timestamp getArrivalTimeStopOver() {
        return ArrivalTimeStopOver;
    }

    public void setArrivalTimeStopOver(Timestamp arrivalTimeStopOver) {
        ArrivalTimeStopOver = arrivalTimeStopOver;
    }

    public Timestamp getDepartureTimeStopOver() {
        return DepartureTimeStopOver;
    }

    public void setDepartureTimeStopOver(Timestamp departureTimeStopOver) {
        DepartureTimeStopOver = departureTimeStopOver;
    }

    public Timestamp getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public PlaneEntity getPlaneCode() {
        return PlaneCode;
    }

    public void setPlaneCode(PlaneEntity planeCode) {
        PlaneCode = planeCode;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Integer getDurationSecondLeg() {
        return DurationSecondLeg;
    }

    public void setDurationSecondLeg(Integer durationSecondLeg) {
        DurationSecondLeg = durationSecondLeg;
    }


    public List<PriceEntity> getPriceList() {
        return PriceList;
    }

    public void setPriceList(List<PriceEntity> priceList) {
        PriceList = priceList;
    }

    public List<LegEntity> getLegEntityList() {
        return LegEntityList;
    }

    public void setLegEntityList(List<LegEntity> legEntityList) {
        LegEntityList = legEntityList;
    }
}
