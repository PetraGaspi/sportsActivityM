package cz.muni.fi.pa165.sportsactivitymanager.Facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;

import java.util.List;

/**
 *
 * @author Petra Gašparíková
 */
public interface UserFacade { 
    Long createUser(UserCreateDTO user);
    void deleteUser(Long id);
    void updateUser(UserDTO user);
    UserDTO getUserById(Long id);
    List<UserDTO> getUsersByName(String name);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();
    Double calculateBMI(UserDTO user);
}
