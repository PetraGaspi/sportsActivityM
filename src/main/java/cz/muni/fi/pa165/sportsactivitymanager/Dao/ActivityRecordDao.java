package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.IActivityRecord;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by michal on 10/27/15.
 */
public class ActivityRecordDao implements IActivityRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(IActivityRecord activityRecord) {
        em.persist(activityRecord);
    }

    @Override
    public ActivityRecord retrieve(int id) {
        return em.find(ActivityRecord.class, id);
    }

    @Override
    public void update(IActivityRecord activityRecord) {
        em.merge(activityRecord);
    }

    @Override
    public void delete(IActivityRecord activityRecord) {
        em.remove(activityRecord);
    }
}
