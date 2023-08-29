package com.workintechS22G1.JPA.dao;

import com.workintechS22G1.JPA.entity.Burger;
import com.workintechS22G1.JPA.entity.enums.BreadType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BurgerDaoImpl implements BurgerDao {
    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager){this.entityManager=entityManager;}

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query=entityManager.createQuery("Select b FROM Burger b",Burger.class);
        return query.getResultList();
    }

    @Override
    public Burger findById(int id) {
        return entityManager.find(Burger.class,id);
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> query =entityManager.createQuery("SELECT b FROM burger b WHERE b.price>=: price  ORDER BY b.price DESC",Burger.class);
        query.setParameter("price",price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM burger b WHERE b.breadTYPE =:breadType ORDER BY name ASC",Burger.class);
        query.setParameter("breadType", BreadType.WHITE);
        return query.getResultList();

    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents ILIKE '%:content%'",Burger.class);
        query.setParameter("content", content);
        return query.getResultList();
    }
    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public void delete(Burger burger) {
        entityManager.remove(burger);
    }
}
