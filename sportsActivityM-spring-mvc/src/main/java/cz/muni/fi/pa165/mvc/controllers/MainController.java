package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityRecordFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by michal on 1/2/16.
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final int RECENT_DAYS = 30;

    @Autowired
    private ActivityRecordFacade recordFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("days", RECENT_DAYS);
        model.addAttribute("records", recordFacade.getRecordsLastDays(RECENT_DAYS));
        return "home";
    }

    @RequestMapping(value = "/{days}", method = RequestMethod.GET)
    public String list(@PathVariable String days, Model model) {
        try {
            int intDays = Integer.parseInt(days);

            model.addAttribute("days", intDays);
            model.addAttribute("records", recordFacade.getRecordsLastDays(intDays));
            return "home";

        } catch (NumberFormatException ne) {
            model.addAttribute("error", true);

            model.addAttribute("days", RECENT_DAYS);
            model.addAttribute("records", recordFacade.getRecordsLastDays(RECENT_DAYS));
            return "home";
        }
    }

}
