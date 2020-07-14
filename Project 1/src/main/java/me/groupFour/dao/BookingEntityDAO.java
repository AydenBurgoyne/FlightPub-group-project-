package me.groupFour.dao;
import me.groupFour.data.BookingEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class BookingEntityDAO extends AbstractEntityDAO<BookingEntity,Integer> implements IBookingEntityDAO {
    @Autowired
    public BookingEntityDAO(EntityManager em) {
        super(BookingEntity.class, em);
    }
}
