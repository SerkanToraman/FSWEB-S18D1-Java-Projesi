package com.workintechS22G1.JPA.dao;

import com.workintechS22G1.JPA.entity.Burger;
import com.workintechS22G1.JPA.entity.enums.BreadType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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
        TypedQuery<Burger> query =entityManager.createQuery("SELECT b FROM Burger b WHERE b.price>=: price  ORDER BY b.price desc",Burger.class);
        query.setParameter("price",price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }


    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        List<Burger> burgers = query.getResultList();
        List<Burger> filteredBurgers = new ArrayList<>();
        for (Burger burger : burgers) {
            List<String> burgerContents = burger.getContents();
            if (burgerContents.stream().anyMatch(c -> c.contains(content))) {
                filteredBurgers.add(burger);
            }
        }
        return filteredBurgers;
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
