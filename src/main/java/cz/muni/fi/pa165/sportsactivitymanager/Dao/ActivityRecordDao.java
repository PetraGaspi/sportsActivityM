package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;

import java.util.Collection;

/**
 * Created by michal on 10/27/15.
 */
public interface ActivityRecordDao {

    void create(ActivityRecord activityRecord);

    ActivityRecord getRecordById(int id);

    Collection<ActivityRecord> getRecordsByUser(User user);

    Collection<ActivityRecord> getRecordsByActivity(Activity activity);

    void update(ActivityRecord activityRecord);

    void delete(ActivityRecord activityRecord);

}
