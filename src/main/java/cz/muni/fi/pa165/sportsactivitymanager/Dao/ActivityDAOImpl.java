/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juraj Pleško, 359530
 */
public class ActivityDAOImpl implements ActivityDAO {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Activity activity) {
        em.persist(activity);
    }

    @Override
    public void update(Activity activity) {
        em.merge (activity);
    }

    @Override
    public void delete(Activity activity) {
        em.remove(activity);
    }

    @Override
    public Activity findById(Long Id) {
        return em.find(Activity.class, Id);
    }

    @Override
    public List<Activity> findAll() {
        return em.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
    }

    @Override
    public List<Activity> findDistance() {
        return em.createQuery("SELECT a FROM Activity a WHERE a.measureDistance = TRUE", Activity.class).getResultList();
    }

    @Override
    public List<Activity> findNonDistance() {
        return em.createQuery("SELECT a FROM Activity a WHERE a.measureDistance = FALSE", Activity.class).getResultList();
    }

    @Override
    public Activity findByName(String name) {
        return em.createQuery("SELECT a FROM Activity a WHERE a.name LIKE :name",Activity.class)
                .setParameter("name",name).getSingleResult();
    }
    
}
