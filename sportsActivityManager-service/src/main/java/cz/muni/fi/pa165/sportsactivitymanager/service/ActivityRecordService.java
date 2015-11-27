package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by michal on 11/21/15.
 */
public interface ActivityRecordService {
    /**
     * Gets all records for the particular activity used by the system users in the last 7 days
     * @param a activity to search records for
     * @param days number rof previous days to search in
     * @return list of all activities for specified criteria
     */
    public List<ActivityRecord> getActivityRecordsLastDays(Activity a, int days);

    /**
     *
     * @param ar
     */
    public void createRecord(ActivityRecord ar);

    /**
     *
     * @param user
     * @return
     */
    List<ActivityRecord> getRecordsByUser(User user);

    /**
     *
     * @param activity
     * @return
     */
    List<ActivityRecord> getRecordsByActivity(Activity activity);

    /**
     *
     * @param activityRecord
     */
    void update(ActivityRecord activityRecord);

    /**
     *
     * @param activityRecord
     */
    void delete(ActivityRecord activityRecord);

    /**
     *
     * @param activityRecordId
     * @return
     */
    ActivityRecord getActivityRecordById(long activityRecordId);
}
