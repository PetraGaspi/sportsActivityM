/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Juraj Pleško, 359530
 */
@Repository
public class CaloriesDAOImpl implements CaloriesDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Calories calories) {
        em.persist(calories);
    }

    @Override
    public void update(Calories calories) {
        em.merge(calories);
    }

    @Override
    public void delete(Calories calories) {
        em.remove(calories);
    }

    @Override
    public Calories findById(Long id) {
        return em.find(Calories.class, id);
    }

    @Override
    public List<Calories> findAll() {
        return em.createQuery("SELECT c FROM Calories c", Calories.class).getResultList();
    }

}
