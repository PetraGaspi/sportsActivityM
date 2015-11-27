package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;

/**
 * Created by michal on 11/26/15.
 */
public interface ActivityService {
    Activity getActivityById(long id);
}
