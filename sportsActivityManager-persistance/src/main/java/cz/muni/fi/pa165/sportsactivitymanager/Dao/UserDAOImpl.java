package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author Petra Gasparikova
 */

@Repository
public class UserDAOImpl implements UserDAO {

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
        em.remove(user);
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
