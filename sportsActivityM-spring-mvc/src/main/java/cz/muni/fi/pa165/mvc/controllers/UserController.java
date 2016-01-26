package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by michal on 12/26/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    final static org.slf4j.Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String filter, Model model) {
        List<UserDTO> users;

        switch (filter) {
            case "all":
                users = userFacade.getAllUsers();
                break;
            case "customers":
                users = userFacade.getUsersByState(UserState.CUSTOMER);
                break;
            case "admins":
                users = userFacade.getUsersByState(UserState.ADMIN);
                break;
            case "inactive":
                users = userFacade.getUsersByState(UserState.INACTIVE);
                break;
            default:
                users = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filter);
        }

        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") long id, Model model) {
        List<UserDTO> users = new ArrayList<>(Arrays.asList(userFacade.getUserById(id)));
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("userCreate", new UserCreateDTO());
        //selectable items for choosing measured distance attribute:
        //model.addAttribute("selectItems", Arrays.asList(true, false));
        return "user/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("userCreate") UserCreateDTO formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);

                log.debug("Error attribute: "+(fe.getField() + "_error"));
            }
            return "user/new";
        }

        //create user
        try {
            Long id = userFacade.createUser(formBean);

            //report success
            redirectAttributes.addFlashAttribute("alert_success", "User " + id + " was created");

        } catch (PersistenceException e){
            log.debug("Supposedly user with email already exists: violates @Unique");

            model.addAttribute("email_error", true);
            bindingResult.addError(new ObjectError("email", "email already exists"));

            return "user/new";
        }
        return "redirect:" + uriBuilder.path("/user/list").toUriString();
    }
}
