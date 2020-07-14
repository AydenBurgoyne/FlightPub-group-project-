package me.groupFour.dao;

import me.groupFour.data.ClassEntity;
import me.groupFour.data.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ClassEntityDAO extends AbstractEntityDAO<ClassEntity,String> implements IClassEntityDAO {
    @Autowired
    public ClassEntityDAO(EntityManager em) {
        super(ClassEntity.class, em);
    }
}