package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
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
    public String newActivity(Model model) {
        model.addAttribute("userCreate", new UserCreateDTO());
        //selectable items for choosing measured distance attribute:
        //model.addAttribute("selectItems", Arrays.asList(true, false));
        return "user/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createActivity(@Valid @ModelAttribute("userCreate") UserCreateDTO formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "user/new";
        }
        //create user
        Long id = userFacade.createUser(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Activity " + id + " was created");
        return "redirect:" + uriBuilder.path("/user/list").toUriString();
    }
}
