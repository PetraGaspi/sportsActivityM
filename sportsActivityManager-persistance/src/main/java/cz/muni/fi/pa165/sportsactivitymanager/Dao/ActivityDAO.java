/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;

import java.util.List;

/**
 * Activity data access object
 *
 * @author Juraj Pleško, 359530
 */
public interface ActivityDAO {

    /**
     * Creates new activity type.
     *
     * @param activity activity to be created
     */
    void create(Activity activity);

    /**
     * Updates activity type.
     *
     * @param activity activity to be updated/changed
     */
    void update(Activity activity);

    /**
     * Removes activity type.
     *
     * @param activity activity to be deleted
     */
    void delete(Activity activity);

    /**
     * Finds activity with the given Id.
     *
     * @param Id Id of the activity to be found
     * @return activity with the given Id
     */
    Activity findById(Long Id);

    /**
     * Finds all activity types.
     *
     * @return list of all activity types
     */
    List<Activity> findAll();

    /**
     * Finds all activities that have distance as input.
     *
     * @return list of all activities with distance value
     */
    List<Activity> findDistance();

    /**
     * Finds all activities that do not depend on distance.
     *
     * @return list of all activities without distance value
     */
    List<Activity> findNonDistance();

    /**
     * Finds all activities with given name.
     *
     * @param name of the activities to be found
     * @return list of all activities with given name
     */
    List<Activity> findByName(String name);


}
