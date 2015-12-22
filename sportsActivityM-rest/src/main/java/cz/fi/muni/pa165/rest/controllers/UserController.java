package cz.fi.muni.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.CaloriesDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
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
import java.util.ArrayList;
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
    public final Collection<UserDTO> getAllUsers() throws JsonProcessingException {
        //debug attempts:
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1l);
        userDTO.setHeight(180d);
        userDTO.setAge(21);
        userDTO.setWeight(75d);
        userDTO.setName("Peter");

        List<UserDTO> out = new ArrayList<>();
        out.add(userDTO);

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
