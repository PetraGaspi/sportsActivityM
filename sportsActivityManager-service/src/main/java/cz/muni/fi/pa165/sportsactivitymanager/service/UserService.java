package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer interface for User.
 *
 * @author Petra Gasparikova
 */

@Service
public interface UserService {

    /**
     * Creates new user.
     *
     * @param user user to create
     * @return new user
     */
    User createUser(User user);

    /**
     * Removes existing user.
     *
     * @param user user to delete
     */
    void deleteUser(User user);

    /**
     * Updates selected user (updates lasts changes of atributes).
     *
     * @param user user to update
     * @return updated user
     */
    User updateUser(User user);

    /**
     * Finds user by his id.
     *
     * @param id id of user to be found
     * @return user with selected id
     */
    User getUserById(Long id);

    /**
     * Finds users by the selected name.
     *
     * @param name name of users to be found
     * @return list of user with the selected name
     */
    List<User> getUserByName(String name);

    /**
     * Finds user by email.
     *
     * @param email email of user to be found
     * @return user with selected email
     */
    User getUserByEmail(String email);

    /**
     * Finds all existing users.
     *
     * @return list of all users
     */
    List<User> getAllUsers();

    /**
     * Finds all users with specified state
     *
     * @param state specified state
     * @return all users with specified state
     */
    List<User> getUsersByState(UserState state);

    /**
     * Calculates Body Mass Index (BMI) of selected user.
     *
     * @param user user to calculate his bmi
     * @return bmi(body mass index)
     */
    Double calculateBMI(User user);

    /**
     * authenticates the user u
     *
     * @param u        user to authenticate
     * @param password auth token
     * @return true if authenticated, otherwise false
     */
    boolean authenticate(User u, String password);
}
