package me.groupFour.dao;

import me.groupFour.data.PlaneEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class PlaneEntityDAO extends AbstractEntityDAO<PlaneEntity,String> implements IPlaneEntityDAO {
    @Autowired
    public PlaneEntityDAO(EntityManager em) {
        super(PlaneEntity.class, em);
    }
}