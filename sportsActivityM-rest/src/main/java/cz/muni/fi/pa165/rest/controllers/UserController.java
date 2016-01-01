package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        List<UserDTO> out = userFacade.getAllUsers();

        return out;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws ResourceNotFoundException {

        UserDTO out = userFacade.getUserById(id);
        if (out == null) {
            throw new ResourceNotFoundException();
        }
        return out;

    }

}
