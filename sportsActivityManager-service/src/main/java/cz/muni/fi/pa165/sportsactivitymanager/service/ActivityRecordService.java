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
     * Gets all records for the particular activity used by the system users in the last days specified as argument
     * @param a activity to search records for
     * @param days number rof previous days to search in
     * @return list of all activities for specified criteria
     */
    public List<ActivityRecord> getActivityRecordsLastDays(Activity a, int days);

    /**
     * Creates a new ActivityRecord
     * @param ar created record
     */
    public void createRecord(ActivityRecord ar);

    /**
     * Gets all persisted records objects
     * @return all persisted records objects
     */
    List<ActivityRecord> getAllRecords();

    /**
     * Finds and returns persisted ActivityRecord objects associated with specified user
     * @param user user associated with retrieved ActivityRecords
     * @return a List of ActivityRecord objects associated with specified user, or empty List if no such object is persisted
     */
    List<ActivityRecord> getRecordsByUser(User user);

    /**
     * Finds and returns persisted ActivityRecord objects associated with specified activity
     * @param activity activity associated with retrieved ActivityRecords
     * @return a List of ActivityRecord objects associated with specified activity, or empty List if no such object is persisted
     */
    List<ActivityRecord> getRecordsByActivity(Activity activity);

    /**
     * Updates an already persisted ActivityRecord object
     * @param activityRecord a new version of already persisted project
     */
    void update(ActivityRecord activityRecord);

    /**
     * Removes an already persisted ActivityRecord object
     * @param activityRecord an instance of the ActivityRecord object that has to be removed from persistence
     */
    void delete(ActivityRecord activityRecord);

    /**
     * Finds and returns persisted ActivityRecord object with specified id
     * @param activityRecordId auto-generated identification of the object
     * @return ActivityRecord object with specified id, or null if no such object exists
     */
    ActivityRecord getActivityRecordById(long activityRecordId);
}
