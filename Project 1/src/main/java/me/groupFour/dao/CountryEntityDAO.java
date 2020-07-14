package me.groupFour.dao;

import me.groupFour.data.BookingEntity;
import me.groupFour.data.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class CountryEntityDAO extends AbstractEntityDAO<CountryEntity,String> implements ICountryEntityDAO {
    @Autowired
    public CountryEntityDAO(EntityManager em) {
        super(CountryEntity.class, em);
    }

    public List<CountryEntity> findCountryWithName(String country){
        return em.createQuery("Select p FROM CountryEntity p where p.countryName = : country", CountryEntity.class)
                .setParameter("country", country)
                .getResultList();
    }
}