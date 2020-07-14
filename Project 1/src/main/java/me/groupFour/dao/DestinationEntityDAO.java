package me.groupFour.dao;

import me.groupFour.data.DestinationEntity;
import me.groupFour.data.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class DestinationEntityDAO extends AbstractEntityDAO<DestinationEntity,String> implements IDestinationEntityDAO {
    @Autowired
    public DestinationEntityDAO(EntityManager em) {
        super(DestinationEntity.class, em);
    }

    public List<DestinationEntity> searchByAirportName(String search){
        return em.createQuery("Select p FROM DestinationEntity p WHERE p.Airport LIKE lower(:searchParam)", DestinationEntity.class)
                .setParameter("searchParam", "%"+search+"%")
                .setMaxResults(10)
                .getResultList();
    }
    public DestinationEntity searchByAirportNameSingle(String search){
        return em.createQuery("Select p FROM DestinationEntity p WHERE p.Airport =:searchParam", DestinationEntity.class)
                .setParameter("searchParam", search)
                .getSingleResult();
    }
}