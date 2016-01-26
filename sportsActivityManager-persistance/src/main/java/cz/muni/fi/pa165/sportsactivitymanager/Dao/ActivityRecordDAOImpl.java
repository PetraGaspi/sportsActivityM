package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.*;
import java.util.HashSet;
import java.util.List;


/**
 * @author Michal Stefanik 422237
 */
@Repository
public class ActivityRecordDAOImpl implements ActivityRecordDAO {

    final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ActivityRecordDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public long create(ActivityRecord activityRecord) {
        //if transient instances are not persisted yet, create them transitively:

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        validator.validate(activityRecord);
        //^^ this supposedly should not pass, though it always does

        //notNull constraint violation verification:
        if (activityRecord.getActivity() != null && activityRecord.getUser() != null) {
            log.debug("attempt to persist subentity activity:"+ activityRecord.getActivity().toString());
            log.debug("attempt to persist subentity user:"+ activityRecord.getUser().toString());

            if (activityRecord.getActivity().getId() == null) {
                //notNull constraint violation verification:
                if (activityRecord.getActivity().getCalories() != null) {
                    if(activityRecord.getActivity().getCalories().getId() == null) {
                        em.persist(activityRecord.getActivity().getCalories());
                    }
                } else {
                    throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>());
                }
                em.persist(activityRecord.getActivity());
            }

            if (activityRecord.getUser().getId() == null) {
                em.persist(activityRecord.getUser());
            }

        } else {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>());
        }
        em.persist(activityRecord);
        return activityRecord.getId();
    }

    @Override
    public List<ActivityRecord> getAllRecords() {
        return em.createQuery("SELECT ar FROM ActivityRecord ar", ActivityRecord.class)
                .getResultList();
    }

    @Override
    public ActivityRecord getRecordById(Long id) {
        return em.find(ActivityRecord.class, id);
    }

    @Override
    public List<ActivityRecord> getRecordsByUser(User user) {
        return em.createQuery("SELECT ar FROM ActivityRecord ar WHERE user.email LIKE :email", ActivityRecord.class)
                .setParameter("email", user.getEmail())
                .getResultList();
    }

    @Override
    public List<ActivityRecord> getRecordsByActivity(Activity activity) {
        return em.createQuery("SELECT ar FROM ActivityRecord ar WHERE activity.name LIKE :name", ActivityRecord.class)
                .setParameter("name", activity.getName())
                .getResultList();
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        em.merge(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        em.remove(em.contains(activityRecord) ? activityRecord : em.merge(activityRecord));
    }
}
