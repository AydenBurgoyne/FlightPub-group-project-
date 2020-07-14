package me.groupFour.dao;

import me.groupFour.data.AirlineEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class AirlineEntityDAO extends AbstractEntityDAO<AirlineEntity,String> implements IAirlineEntityDAO {
    @Autowired
    public AirlineEntityDAO(EntityManager em) {
        super(AirlineEntity.class, em);
    }
}
