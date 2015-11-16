package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;

import java.util.List;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
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
    public void update(User user) {
        em.merge(user);
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
        return em.createQuery("SELECT u FROM User u WHERE u.name LIKE :name",User.class)
                .setParameter("name",name).getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return em.createQuery("SELECT u FROM User u WHERE u.email LIKE :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    
    
}
