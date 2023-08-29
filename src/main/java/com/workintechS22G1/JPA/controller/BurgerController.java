package com.workintechS22G1.JPA.controller;

import com.workintechS22G1.JPA.dao.BurgerDao;
import com.workintechS22G1.JPA.dao.BurgerDaoImpl;
import com.workintechS22G1.JPA.entity.Burger;
import com.workintechS22G1.JPA.entity.enums.BreadType;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
@Validated
public class BurgerController {
    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping("/")
    public List<Burger> findAll(){return burgerDao.findAll();}

    @GetMapping("/{id}")
    public Burger find(@Positive @PathVariable int id){ return burgerDao.findById(id);}

    @GetMapping("/findByPrice/{price}")
    public List<Burger> findByPrice(@PathVariable int price){
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/findByContent/{content}")
    public List<Burger> findByContent(@PathVariable String content){
        return burgerDao.findByContent(content);
    }

    @GetMapping("/findByBreadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable String breadType){
        BreadType breadTypeEnum = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(breadTypeEnum);
    }

    @PostMapping("/")
    public Burger save(@Validated @RequestBody Burger burger){return burgerDao.save(burger);}

    @PutMapping("/")
    public Burger update(@RequestBody Burger burger){return burgerDao.update(burger);}

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable int id){
        Burger burger =find(id);
        burgerDao.delete(burger);
        return burger;
    }
}

