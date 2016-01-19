package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by michal on 1/2/16.
 */
@Controller
@RequestMapping("/secured/auth/activity")
public class AdminActivityController {
    @Autowired
    private ActivityFacade activityFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("activities", activityFacade.getAllActivities());
        return "secured/auth/activity/list";
    }

    @RequestMapping(value = "/{action}/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String action, @PathVariable Integer filter, Model model,
                       RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        switch (action) {
            case "delete":
                activityFacade.deleteActivity(activityFacade.findActivityById((long) filter));
                redirectAttributes.addFlashAttribute("alert_success", "Activity " + filter + " was deleted");
                return "redirect:" + uriBuilder.path("/secured/auth/activity/list").toUriString();
            default:
                return "redirect:" + uriBuilder.path("/secured/auth/activity/list").toUriString();
        }
    }
}
