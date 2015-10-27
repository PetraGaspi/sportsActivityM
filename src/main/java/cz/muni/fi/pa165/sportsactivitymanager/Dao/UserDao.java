package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import  cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import java.util.List;

/**
 *
 * @author Petra Gasparikova
 */
public interface UserDao {
    
    void create(User user);
    void update(User user);
    void delete(User user);
    User findById(Long id); 
    List<User> findByName(String name);
    User findByEmail(String email);
    List<User> findAll();
}
