package me.groupFour.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class EntityManagerCreator {
    private EntityManagerFactory emf;
    public EntityManagerCreator(){
         emf = Persistence.createEntityManagerFactory("my-app");


    }

    public EntityManager create(){
        return emf.createEntityManager();
    }
}
