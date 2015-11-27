package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityRecordDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Petra Gasparikova
 */

@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Autowired
    private ActivityDAO userDao;

    @Override
    public Activity getActivityById(long id) {
        return null;
    }
}
