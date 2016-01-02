package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by michal on 11/26/15.
 */
@Service
public interface ActivityService {

    public void createActivity(Activity ac);

    Activity getActivityById(long id);

    void updateActivity(Activity ac);

    void deleteActivity(Activity ac);

    Activity findActivityById(long id);

    List<Activity> findAllActivities();

    List<Activity> findAllDistance();

    List<Activity> findAllNonDistance();

    List<Activity> findByName(String name);
}
