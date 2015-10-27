package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.IActivityRecord;

/**
 * Created by michal on 10/27/15.
 */
public interface IActivityRecordDao {

    void create(IActivityRecord activityRecord);

    ActivityRecord retrieve(int id);

    void update(IActivityRecord activityRecord);

    void delete(IActivityRecord activityRecord);

}
