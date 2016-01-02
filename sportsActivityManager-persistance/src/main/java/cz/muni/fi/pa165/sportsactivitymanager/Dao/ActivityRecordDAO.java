package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;

import java.util.List;

/**
 * @author Michal Stefanik 422237
 */
public interface ActivityRecordDAO {

    /**
     * Persists a new ActivityRecord object
     *
     * @param activityRecord
     */
    void create(ActivityRecord activityRecord);

    /**
     * Gets all persisted records objects
     *
     * @return all persisted records objects
     */
    List<ActivityRecord> getAllRecords();

    /**
     * Finds and returns persisted ActivityRecord object with specified id
     *
     * @param id auto-generated identification of the object
     * @return ActivityRecord object with specified id, or null if no such object exists
     */
    ActivityRecord getRecordById(Long id);

    /**
     * Finds and returns persisted ActivityRecord objects associated with specified user
     *
     * @param user user associated with retrieved ActivityRecords
     * @return a List of ActivityRecord objects associated with specified user, or empty List if no such object is persisted
     */
    List<ActivityRecord> getRecordsByUser(User user);

    /**
     * Finds and returns persisted ActivityRecord objects associated with specified activity
     *
     * @param activity activity associated with retrieved ActivityRecords
     * @return a List of ActivityRecord objects associated with specified activity, or empty List if no such object is persisted
     */
    List<ActivityRecord> getRecordsByActivity(Activity activity);

    /**
     * Updates an already persisted ActivityRecord object
     *
     * @param activityRecord a new version of already persisted project
     */
    void update(ActivityRecord activityRecord);

    /**
     * Removes an already persisted ActivityRecord object
     *
     * @param activityRecord an instance of the ActivityRecord object that has to be removed from persistence
     */
    void delete(ActivityRecord activityRecord);


}
