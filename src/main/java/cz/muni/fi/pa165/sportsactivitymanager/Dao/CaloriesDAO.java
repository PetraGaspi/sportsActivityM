/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import java.util.List;

/**
 *
 * @author Juraj Pleško, 359530
 */
public interface CaloriesDAO {

    void create (Calories calories);
    void update (Calories calories);
    void delete (Calories calories);
    Calories findById (Long id);
    List findAll();
    
    
}
