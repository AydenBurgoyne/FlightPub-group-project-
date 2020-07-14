package me.groupFour.dao;

import me.groupFour.data.LegEntity;
import me.groupFour.data.PlaneEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class LegEntityDAO extends AbstractEntityDAO<LegEntity,Integer> implements ILegEntityDAO {
    @Autowired
    public LegEntityDAO(EntityManager em) {
        super(LegEntity.class, em);
    }
}