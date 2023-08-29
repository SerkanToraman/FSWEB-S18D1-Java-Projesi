package com.workintechS22G1.JPA.dao;

import com.workintechS22G1.JPA.entity.Burger;
import com.workintechS22G1.JPA.entity.enums.BreadType;

import java.util.List;

public interface BurgerDao {
    Burger save(Burger burger);
    List<Burger> findAll();
    Burger findById(int id);
    List<Burger> findByPrice(double price);
    List<Burger> findByBreadType(BreadType breadType);
    List<Burger> findByContent(String content);
    Burger update(Burger burger);
    void delete(Burger burger);
}
