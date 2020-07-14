package me.groupFour.dao;

import me.groupFour.data.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class AccountEntityDAO extends AbstractEntityDAO<AccountEntity,String> implements IAccountEntityDAO{
    @Autowired
    public AccountEntityDAO(EntityManager em){
        super(AccountEntity.class, em);
    }

}
