package cz.muni.fi.pa165.sportsactivitymanager.service.facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Petra Gasparikova
 */

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createUser(UserCreateDTO user) {
        User u = new User();
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setPasswordHash(userService.createHash(user.getPassword()));
        u.setState(UserState.CUSTOMER);
        u.setSex(user.getSEX());
        u.setWeight(user.getWeight());
        u.setHeight(user.getHeight());
        u.setAge(user.getAge());
        u.setState(user.getState());
        userService.createUser(u);
        return u.getId();
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(new User(id));
    }

    @Override
    public void updateUser(UserDTO user) {
        userService.updateUser(
                beanMappingService.mapTo(user, User.class)
        );
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userService.getUserById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsersByName(String name) {
        List<User> users = userService.getUserByName(name);
        return (users == null) ? null : beanMappingService.mapTo(users, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsersByState(UserState state) {
        List<User> users = userService.getUsersByState(state);

        return (users == null) ? null : beanMappingService.mapTo(users, UserDTO.class);
    }

    @Override
    public Double calculateBMI(UserDTO user) {
        return userService.calculateBMI(beanMappingService.mapTo(user, User.class));
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO userAuthDTO) {
        return userService.authenticate(userService.getUserById(userAuthDTO.getId()), userAuthDTO.getPassword());
    }

    @Override
    public String getUserSession(UserDTO user){
        return userService.getUserSession(beanMappingService.mapTo(user, User.class));
    }


}
























