package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Petra Gasparikova
 */

@Repository
public class UserDAOImpl implements UserDAO {

    final static org.slf4j.Logger log = LoggerFactory.getLogger(UserDAOImpl.class);


    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void delete(User user) {
        List<ActivityRecord> associatedRecords = em.createQuery("SELECT ar FROM ActivityRecord ar WHERE user.id = :id", ActivityRecord.class)
                .setParameter("id", user.getId())
                .getResultList();

        for(ActivityRecord record: associatedRecords ){
            em.remove(record);
        }
        log.debug("deleted associated records for user "+user.getId()+" : "+associatedRecords.size());

        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findByName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.name LIKE :name", User.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return em.createQuery("SELECT u FROM User u WHERE u.email LIKE :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> findByState(UserState state) {
        return em.createQuery("SELECT u FROM User u WHERE u.state = :state", User.class)
                .setParameter("state", state)
                .getResultList();
    }


}
