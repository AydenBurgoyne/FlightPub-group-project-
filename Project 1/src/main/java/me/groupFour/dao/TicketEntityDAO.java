package me.groupFour.dao;

import me.groupFour.data.LegEntity;
import me.groupFour.data.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class TicketEntityDAO extends AbstractEntityDAO<TicketEntity,String> implements ITicketEntityDAO {
    @Autowired
    public TicketEntityDAO(EntityManager em) {
        super(TicketEntity.class, em);
    }
}