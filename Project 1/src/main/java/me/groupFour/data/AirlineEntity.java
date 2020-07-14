package me.groupFour.data;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="airlines")
public class AirlineEntity {
    @Id
    @Basic(optional =false)
    private String AirlineCode;
    @Basic(optional=false)
    private String AirlineName;
    @Basic(optional =false)
    @ManyToOne()
    @JoinColumn(name="CountryCode3")
    private CountryEntity CountryCode3;
    @OneToMany(mappedBy="AirlineCode")
    private List<FlightEntity> FlightEntityList;
    @OneToMany(mappedBy = "AirlineCode")
    private List<PriceEntity> PriceEntityList;

    public AirlineEntity(String airlineCode, String airlineName, CountryEntity countryCode3, List<FlightEntity> flightEntityList, List<PriceEntity> priceEntityList) {
        AirlineCode = airlineCode;
        AirlineName = airlineName;
        CountryCode3 = countryCode3;
        FlightEntityList = flightEntityList;
        PriceEntityList = priceEntityList;
    }

    public AirlineEntity() {

    }

    public String getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        AirlineCode = airlineCode;
    }

    public String getAirlineName() {
        return AirlineName;
    }

    public void setAirlineName(String airlineName) {
        AirlineName = airlineName;
    }

    public CountryEntity getCountryCode3() {
        return CountryCode3;
    }

    public void setCountryCode3(CountryEntity countryCode3) {
        CountryCode3 = countryCode3;
    }

    public List<FlightEntity> getFlightEntityList() {
        return FlightEntityList;
    }

    public void setFlightEntityList(List<FlightEntity> flightEntityList) {
        FlightEntityList = flightEntityList;
    }

    public List<PriceEntity> getPriceEntityList() {
        return PriceEntityList;
    }

    public void setPriceEntityList(List<PriceEntity> priceEntityList) {
        PriceEntityList = priceEntityList;
    }
}
