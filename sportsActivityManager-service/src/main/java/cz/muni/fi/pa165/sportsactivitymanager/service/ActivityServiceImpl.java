package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Petra Gasparikova & Juraj Pleško
 */

@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Inject
    ActivityDAO dao;

    //TODO: edit to make unique naming (find/get)x
    @Override
    public Activity getActivityById(long id) {
        return dao.findById(id);
    }

    @Override
    public void createActivity(Activity ac) {
        dao.create(ac);
    }

    @Override
    public void updateActivity(Activity ac) {
        dao.update(ac);
    }

    @Override
    public void deleteActivity(Activity ac) {
        dao.delete(ac);
    }

    @Override
    public Activity findActivityById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<Activity> findAllActivities() {
        return dao.findAll();
    }

    @Override
    public List<Activity> findAllDistance() {
        return dao.findDistance();
    }

    @Override
    public List<Activity> findAllNonDistance() {
        return dao.findNonDistance();
    }

    @Override
    public List<Activity> findByName(String name) {
        return dao.findByName(name);
    }
}
