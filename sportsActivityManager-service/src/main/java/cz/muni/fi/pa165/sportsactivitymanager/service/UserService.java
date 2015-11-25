package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
//import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Petra Gasparikova
 */

@Service
public interface UserService {
    
    void createUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    User getUserById(Long id);
    List<User> getUserByName(String name);
    User getUserByEmail(String email);
    List<User> getAllUsers();   
}
