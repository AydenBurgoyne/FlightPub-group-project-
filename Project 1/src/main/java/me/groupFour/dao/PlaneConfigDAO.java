package me.groupFour.dao;

import me.groupFour.data.AirlineEntity;
import me.groupFour.data.planeSeatingArrangementsEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class PlaneConfigDAO extends AbstractEntityDAO<planeSeatingArrangementsEntity,String> {
    @Autowired
    public PlaneConfigDAO(EntityManager em) {
        super(planeSeatingArrangementsEntity.class, em);
    }
}
