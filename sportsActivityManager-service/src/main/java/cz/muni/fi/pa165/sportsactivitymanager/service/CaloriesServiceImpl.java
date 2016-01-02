/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Juraj Pleško, 359530
 */
@Service
public class CaloriesServiceImpl implements CaloriesService {

    @Inject
    CaloriesDAO dao;

    @Override
    public void createCalories(Calories ca) {
        dao.create(ca);

    }

    @Override
    public void updateCalories(Calories ca) {
        dao.update(ca);
    }

    @Override
    public void deleteCalories(Calories ca) {
        dao.delete(ca);
    }

    @Override
    public Calories getCaloriesById(long id) {
        return dao.findById(id);
    }

}
