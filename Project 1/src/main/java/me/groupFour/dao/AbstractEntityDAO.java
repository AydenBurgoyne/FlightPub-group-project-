package me.groupFour.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public abstract class AbstractEntityDAO<EntityType, KeyType> implements IEntityDAO<EntityType, KeyType> {

    //might need to change to final

    protected Class<EntityType> cls; //class type that is being managed.
    protected EntityManager em;

    public AbstractEntityDAO(Class<EntityType> cls,EntityManager em){
        this.cls = cls;
        this.em = em;
    }

    @Override
    public void create(EntityType entity){
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public List<EntityType> findAll(int page, int length){
        return em.createQuery("select x From "+cls.getName()+ " x", cls).setFirstResult(page*length).setMaxResults(length).getResultList();
    }

    @Override
    public EntityType findById(KeyType id) {
        return em.find(cls,id);
    }

    public List<EntityType> queryAll(String query, Map<String,Object> params, int page, int length){
        StringBuilder jqlQuery = new StringBuilder().append("Select x From ").append(cls.getName()).append("x Where ").append(query);

        TypedQuery<EntityType> emQuery = em.createQuery(jqlQuery.toString(),cls);
        for(Map.Entry<String,Object> entry : params.entrySet()){
            emQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return emQuery.setFirstResult(page*length).setMaxResults(length).getResultList();

    }

    public EntityType queryOne(String query, Map<String,Object> params){
        StringBuilder jqlQuery = new StringBuilder().append("Select x From ").append(cls.getName()).append("x Where ").append(query);

        TypedQuery<EntityType> emQuery = em.createQuery(jqlQuery.toString(),cls);
        for(Map.Entry<String,Object> entry : params.entrySet()){
            emQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return emQuery.getSingleResult();

    }

    public EntityType update(EntityType entity){
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();
        return entity;

    }

    public void delete(EntityType entity){
        em.remove(entity);
    }

    public void deleteById(KeyType key){
        delete(findById(key));
    }
}
