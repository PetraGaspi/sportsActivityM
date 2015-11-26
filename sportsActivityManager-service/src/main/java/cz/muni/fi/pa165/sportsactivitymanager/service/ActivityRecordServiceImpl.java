package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityRecordDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by michal on 11/21/15.
 */

@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    ActivityRecordDAO dao;

    @Override
    public List<ActivityRecord> getActivityRecordsLastDays(Activity a, int days) {
        Calendar acceptedTimeLimit = Calendar.getInstance();
        acceptedTimeLimit.add(Calendar.DAY_OF_MONTH, days);

        List<ActivityRecord> all = dao.getRecordsByActivity(a);
        List<ActivityRecord> filtered = new ArrayList<>();
        for(ActivityRecord record : all){
            if(record.getDate().after(acceptedTimeLimit)){
                all.add(record);
            }
        }

        return filtered;
    }

    @Override
    public void createRecord(ActivityRecord ar) {
        dao.create(ar);
    }

    @Override
    public List<ActivityRecord> getRecordsByUser(User user) {
        return dao.getRecordsByUser(user);
    }

    @Override
    public List<ActivityRecord> getRecordsByActivity(Activity activity) {
        return dao.getRecordsByActivity(activity);
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        dao.update(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        dao.delete(activityRecord);
    }

    @Override
    public ActivityRecord getActivityRecordById(long activityRecordId) {
        return dao.getRecordById(activityRecordId);
    }
}
