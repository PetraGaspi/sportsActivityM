/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;

import java.util.List;

/**
 * Calories data access object
 *
 * @author Juraj Pleško, 359530
 */
public interface CaloriesDAO {
    /**
     * Creates new calories object.
     *
     * @param calories calories to be created
     */
    void create(Calories calories);

    /**
     * Updates calories object.
     *
     * @param calories calories to be updated/changed
     */
    void update(Calories calories);

    /**
     * Deletes calories object.
     *
     * @param calories calories to be deleted
     */
    void delete(Calories calories);

    /**
     * Finds calories with given Id.
     *
     * @param id Id of the calories to be found
     * @return calories with the given id
     */
    Calories findById(Long id);

    /**
     * Lists all calories
     *
     * @return List of all calories
     */
    List<Calories> findAll();


}
