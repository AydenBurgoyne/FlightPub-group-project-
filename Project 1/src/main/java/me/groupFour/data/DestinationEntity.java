package me.groupFour.data;

import javax.persistence.*;
import java.util.List;
@Entity
//Used to map Destinations from the database using hibernate.
@Table(name="Destinations")
public class DestinationEntity {
    @Id
    @Column(length=3)
    private String DestinationCode;
    private String Airport;
    @JoinColumn(name="CountryCode3")
    @ManyToOne
    private CountryEntity CountryCode3;
    @OneToMany(mappedBy = "DestinationCode")
    private List<FlightEntity> FlightDestinationList;
    @OneToMany(mappedBy="DepartureCode")
    private List<FlightEntity> FlightDepartureList;
    @OneToMany(mappedBy="StopOverCode")
    private List<FlightEntity> FlightStopOverList;

    public DestinationEntity() {
    }

    public DestinationEntity(String destinationCode, String airport, CountryEntity countryCode3, List<FlightEntity> flightDestinationList, List<FlightEntity> flightDepartureList, List<FlightEntity> flightStopOverList) {
        DestinationCode = destinationCode;
        Airport = airport;
        CountryCode3 = countryCode3;
        FlightDestinationList = flightDestinationList;
        FlightDepartureList = flightDepartureList;
        FlightStopOverList = flightStopOverList;
    }

    public void setDestinationCode(String destinationCode) {
        DestinationCode = destinationCode;
    }

    public void setAirport(String airport) {
        Airport = airport;
    }

    public void setCountryCode3(CountryEntity countryCode3) {
        CountryCode3 = countryCode3;
    }

    public void setFlightDestinationList(List<FlightEntity> flightDestinationList) {
        FlightDestinationList = flightDestinationList;
    }

    public void setFlightDepartureList(List<FlightEntity> flightDepartureList) {
        FlightDepartureList = flightDepartureList;
    }

    public void setFlightStopOverList(List<FlightEntity> flightStopOverList) {
        FlightStopOverList = flightStopOverList;
    }

    public String getDestinationCode() {
        return DestinationCode;
    }

    public String getAirport() {
        return Airport;
    }

    public CountryEntity getCountryCode3() {
        return CountryCode3;
    }

    public List<FlightEntity> getFlightDestinationList() {
        return FlightDestinationList;
    }

    public List<FlightEntity> getFlightDepartureList() {
        return FlightDepartureList;
    }

    public List<FlightEntity> getFlightStopOverList() {
        return FlightStopOverList;
    }
}
