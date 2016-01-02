package cz.muni.fi.pa165.sportsactivitymanager.Facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade layer interface for User.
 * 
 * @author Petra Gašparíková
 */
@Service
public interface UserFacade { 

    /**
     * Creates new user.
     *
     * @param user DTO of user to be created
     * @return id of new user
     */
    Long createUser(UserCreateDTO user);

    /**
     * Removes user with selected id.
     * 
     * @param id id of user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Updates selected user.
     * 
     * @param user  DTO of user to be updated
     */
    void updateUser(UserDTO user);

    /**
     * Finds user by id.
     * 
     * @param id id of user to find
     * @return DTO of user with selected id
     */
    UserDTO getUserById(Long id);

    /**
     * Finds users by selected name.
     * 
     * @param name name of users to be found
     * @return List of DTOs of users with selected name
     */
    List<UserDTO> getUsersByName(String name);

    /**
     * Finds user by selected email.
     * 
     * @param email email of user to be found
     * @return DTO of user with selected email
     */
    UserDTO getUserByEmail(String email);

    /**
     * Finds all existing users.
     * 
     * @return List of DTOs of all users
     */
    List<UserDTO> getAllUsers();

    /**
     * Finds all users with specified state
     * @param state specified state
     * @return all users with specified state
     */
    List<UserDTO> getUsersByState(UserState state);


    /**
     * Calculates Body Mass Index (BMI) of selected user.
     * 
     * @param user  DTO of user to calculate his bmi
     * @return BMI
     */
    Double calculateBMI(UserDTO user);

    boolean authenticate(UserAuthenticateDTO userAuthDTO);
}
