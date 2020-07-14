package me.groupFour.dao;

import me.groupFour.data.JourneyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;

public class JourneyEntityDAO extends AbstractEntityDAO<JourneyEntity,String> implements IJourneyEntityDAO {
    @Autowired
    public JourneyEntityDAO(EntityManager em){
        super(JourneyEntity.class, em);
    }
}