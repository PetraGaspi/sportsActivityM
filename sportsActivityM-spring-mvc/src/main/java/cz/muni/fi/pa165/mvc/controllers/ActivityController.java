package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityFacade;
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
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityFacade activityFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("activities", activityFacade.getAllActivities());
        return "activity/list";
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String filter, Model model) {
        //TODO: might be expandable with more filters

        List<ActivityDTO> users;
        switch (filter) {
            case "all":
                users = activityFacade.getAllActivities();
                break;
            default:
                users = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filter);

        }
        model.addAttribute("activities", users);
        return "activity/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getActivity(@PathVariable("id") long id, Model model) {
        List<ActivityDTO> users = new ArrayList<>(Arrays.asList(activityFacade.findActivityById(id)));
        model.addAttribute("activities", users);
        return "activity/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newActivity(Model model) {
        model.addAttribute("activityCreate", new ActivityCreateDTO());
        //selectable items for choosing measured distance attribute:
        //model.addAttribute("selectItems", Arrays.asList(true, false));
        return "activity/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createActivity(@Valid @ModelAttribute("activityCreate") ActivityCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "activity/new";
        }
        //create activity
        Long id = activityFacade.createActivity(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Activity " + id + " was created");
        return "redirect:" + uriBuilder.path("/activity/list").toUriString();
    }
}
