/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import java.util.List;

/**
 *
 * @author Juraj Pleško, 359530
 */
public interface ActivityDAO {
    
    void create (Activity activity);
    void update (Activity activity);
    void delete (Activity activity);
    Activity findById(Long Id);
    List<Activity> findAll();
    List<Activity> findDistance();
    List<Activity> findNonDistance();
    Activity findByName(String name);
    
    
}
