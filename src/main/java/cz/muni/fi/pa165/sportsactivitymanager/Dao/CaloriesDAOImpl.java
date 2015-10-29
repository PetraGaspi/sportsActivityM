/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juraj Pleško, 359530
 */
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
    public List findAll() {
        return em.createQuery("SELECT u FROM Calories c", Calories.class).getResultList();
    }
    
}
