package cz.fi.muni.pa165.rest.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by michal on 12/17/15.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDTO> getAllUsers() {
//            //create mock data: TODO implement in sample-data module
//            User user = new User();
//            user.setName("Peter");
//            user.setWeight(81d);
//            user.setAge(21);
//            user.setHeight(185d);
//            user.setEmail("peter2@gmail.com");
//
//            userDAO.create(user);
//            System.out.println(user.getEmail() + "attached id: " + user.getId());


            List<UserDTO> out = userFacade.getAllUsers();

            return out;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws ResourceNotFoundException {

        try {
            return userFacade.getUserById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }

}
