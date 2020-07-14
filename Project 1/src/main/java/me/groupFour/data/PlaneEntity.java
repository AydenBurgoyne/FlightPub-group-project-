package me.groupFour.data;

import javax.persistence.*;
import java.util.List;
//Plane Entity type is used to map information about the plane from the database using hibernate.
@Entity
@Table(name="planetype")
public class PlaneEntity {
    @Id
    private String PlaneCode;
    private String Details;
    private Integer NumFirstClass;
    private Integer NumBusiness;
    private Integer NumPremiumEconomy;
    private Integer Economy;
    @OneToMany(mappedBy="PlaneCode")
    private List<FlightEntity> FlightList;
    @OneToMany(mappedBy="planeType")
    //This stores information about the planes seating arrangement used for the bookingmap.
    private List<planeSeatingArrangementsEntity> PlaneList;

    public PlaneEntity() {
    }

    public PlaneEntity(String planeCode, String details, Integer numFirstClass, Integer numBusiness, Integer numPremiumEconomy, Integer economy) {
        PlaneCode = planeCode;
        Details = details;
        NumFirstClass = numFirstClass;
        NumBusiness = numBusiness;
        NumPremiumEconomy = numPremiumEconomy;
        Economy = economy;

    }

    public List<FlightEntity> getFlightList() {
        return FlightList;
    }

    public void setFlightList(List<FlightEntity> flightList) {
        FlightList = flightList;
    }

    public List<planeSeatingArrangementsEntity> getPlaneList() {
        return PlaneList;
    }

    public void setPlaneList(List<planeSeatingArrangementsEntity> planeList) {
        PlaneList = planeList;
    }

    public String getPlaneCode() {
        return PlaneCode;
    }

    public void setPlaneCode(String planeCode) {
        PlaneCode = planeCode;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Integer getNumFirstClass() {
        return NumFirstClass;
    }

    public void setNumFirstClass(Integer numFirstClass) {
        NumFirstClass = numFirstClass;
    }

    public Integer getNumBusiness() {
        return NumBusiness;
    }

    public void setNumBusiness(Integer numBusiness) {
        NumBusiness = numBusiness;
    }

    public Integer getNumPremiumEconomy() {
        return NumPremiumEconomy;
    }

    public void setNumPremiumEconomy(Integer numPremiumEconomy) {
        NumPremiumEconomy = numPremiumEconomy;
    }

    public Integer getEconomy() {
        return Economy;
    }

    public void setEconomy(Integer economy) {
        Economy = economy;
    }
}
