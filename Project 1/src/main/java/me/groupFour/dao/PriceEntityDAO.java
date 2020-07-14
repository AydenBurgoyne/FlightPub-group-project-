package me.groupFour.dao;

import me.groupFour.data.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.sql.Timestamp;

public class PriceEntityDAO extends AbstractEntityDAO<PriceEntity,Integer> implements IPriceEntityDAO {
    @Autowired
    public PriceEntityDAO(EntityManager em) {
        super(PriceEntity.class, em);
    }

    public PriceEntity findPrice(Timestamp currentTime, FlightEntity flightnumber, ClassEntity classcode, TicketEntity ticketcode,AirlineEntity airlinecode){
            PriceEntity temp = em.createQuery("Select p FROM PriceEntity p where p.FlightNumber = :flightnumber and p.ClassCode = :classcode and p.TicketCode =:ticketcode and :currentTime BETWEEN p.StartDate and p.EndDate", PriceEntity.class)
                    .setParameter("currentTime", currentTime)
                    .setParameter("flightnumber", flightnumber.getFlightNumber())
                    .setParameter("classcode", classcode)
                    .setParameter("ticketcode", ticketcode)
                    //.setParameter("airline", airlinecode)
                    .getSingleResult();
             return temp;
    }
}
//
//    public PriceEntity findPrice(Timestamp currentTime, FlightEntity flightnumber, ClassEntity classcode, TicketEntity ticketcode,AirlineEntity airlinecode){
//        Query q = em.createNativeQuery("Select PriceEntity.* from price where FlightNumber=?flight and ClassCode =?classc and TicketCode =?ticket and AirlineCode =?airline and ?cTime between StartDate and EndDate");
//        q.setParameter("cTime", currentTime.getTime());
//        q.setParameter("flight", flightnumber.getFlightNumber());
//        q.setParameter("classc", classcode.getClassCode());
//        q.setParameter("ticket", ticketcode.getTicketCode());
//        q.setParameter("airline", airlinecode.getAirlineCode());
//        PriceEntity temp = (PriceEntity)q.getSingleResult();
//        return temp;
