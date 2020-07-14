package me.groupFour.dao;

import me.groupFour.data.CountryEntity;
import me.groupFour.data.DestinationEntity;
import me.groupFour.data.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class FlightEntityDAO extends AbstractEntityDAO<FlightEntity,Integer> implements IFlightEntityDAO {
    DestinationEntityDAO a;
    @Autowired

    public FlightEntityDAO(EntityManager em){
        super(FlightEntity.class, em);
    }
    public List<FlightEntity> searchFlight(Timestamp RangeStart,Timestamp RangeEnd,DestinationEntity dest1,DestinationEntity dest2){

        return em.createQuery("Select p FROM FlightEntity p where p.DepartureCode = :dest1 and p.DestinationCode = :dest2 and p.DepartureTime BETWEEN :RangeStart and :RangeEnd", FlightEntity.class)
                .setParameter("RangeStart", RangeStart)
                .setParameter("RangeEnd",RangeEnd)
                .setParameter("dest1",dest1)
                .setParameter("dest2",dest2)
                .getResultList();
    }

    //generates a separate flight entity based on all of the distinct entries into the batabase
    //the method does not include any values that have a value that is null, so two separate lists are needed
    //adding the list includes both flgihts that has 2 destinations and 3 destinations
    @Override
    public List<FlightEntity> getAllDistinct() {
        List<FlightEntity> directList = em.createQuery("select distinct new FlightEntity (x.DepartureCode, x.DestinationCode, x.Duration) from FlightEntity x where StopOverCode = null", cls).getResultList();
        List<FlightEntity> stopOverList = em.createQuery("select distinct new FlightEntity(x.DepartureCode, x.StopOverCode, x.DestinationCode, x.Duration, x.DurationSecondLeg) from FlightEntity x", FlightEntity.class).getResultList();
        directList.addAll(stopOverList);
        return directList;
    }
}