package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityRecordDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityRecordDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Petra Gasparikova & Juraj Pleško
 */

@Service
public class ActivityServiceImpl implements ActivityService {

    final static Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Inject
    ActivityDAO dao;

    @Inject
    ActivityRecordDAO recordDao;

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
        //also deletes all activity records bound to this activity so notNull constraints are not violated
        List<ActivityRecord> boundRecords = recordDao.getRecordsByActivity(ac);
        log.debug("found records for activity "+ac.getName()+": "+boundRecords.size());

        for(ActivityRecord record : boundRecords){
            recordDao.delete(record);
        }

        log.debug("after delete found records for activity "+ac.getName()+" : "+boundRecords.size());

        //finally delete the activity
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
