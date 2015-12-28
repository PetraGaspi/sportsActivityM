package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import  cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;

import java.util.List;

/**
 * User data access object
 * 
 * @author Petra Gasparikova
 */
public interface UserDAO {
    
    /**
     * Creates new user in database
     * 
     * @param user user to be created in db
     */
    void create(User user);

    /**
     * Updates existing user in database
     *
     * @param user user to be updated
     */
    User update(User user);

    /**
     * Removes user from database
     * 
     * @param user user to be deleted
     */
    void delete(User user);

    /**
     * Finds user by his ID
     * 
     * @param id id of user
     * @return user with specified id, null if such user does not exist
     */
    User findById(Long id); 

    /**
     *Finds list of users with selected name
     * 
     * @param name name of user
     * @return list of users with specified name, null if such user does not exist
     */
    List<User> findByName(String name);

    /**
     * 
     * Finds user by his email address
     *
     * @param email email address of user
     * @return user with specified email address, null if such user does not exist
     */
    User findByEmail(String email);

    /**
     * Finds all users in database
     * 
     * @return list of users
     */
    List<User> findAll();

    /**
     * Finds all registered users with specified state
     * @param state specified state
     * @return all registered users with specified state
     */
    List<User> findByState(UserState state);
}
