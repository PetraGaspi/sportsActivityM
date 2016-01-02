package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getAllUsers() {

        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getById(@PathVariable("id") long id) throws ResourceNotFoundException {

        UserDTO out = userFacade.getUserById(id);
        if (out == null) {
            throw new ResourceNotFoundException();
        }
        return out;
    }

    @RequestMapping(value = "/state/{filter}", method = RequestMethod.GET)
    public List<UserDTO> getByState(@PathVariable String filter) {

        switch (filter) {
            case "all":
                return userFacade.getAllUsers();
            case "customers":
                return userFacade.getUsersByState(UserState.CUSTOMER);
            case "active":
                List<UserDTO> out = userFacade.getUsersByState(UserState.CUSTOMER);
                out.addAll(userFacade.getUsersByState(UserState.ADMIN));
                return out;
            case "admins":
                return userFacade.getUsersByState(UserState.ADMIN);
            case "inactive":
                return userFacade.getUsersByState(UserState.INACTIVE);
            default:
                return userFacade.getAllUsers();
        }
    }

    @RequestMapping(value = "/name/{filter}", method = RequestMethod.GET)
    public List<UserDTO> list(@PathVariable String filter) {
        return userFacade.getUsersByName(filter);
    }

}
