package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityRecordCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityRecordDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityFacade;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityRecordFacade;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by michal on 12/26/15.
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private ActivityRecordFacade recordFacade;

    @Autowired
    private ActivityFacade activityFacade;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("records", recordFacade.getAllRecords());
        return "record/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRecord(Model model) {
        model.addAttribute("recordCreate", new ActivityRecordCreateDTO());
        model.addAttribute("users", userFacade.getAllUsers());
        model.addAttribute("activities", activityFacade.getAllActivities());
        //selectable items for choosing measured distance attribute:
        //model.addAttribute("selectItems", Arrays.asList(true, false));
        return "record/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createRecord(@Valid @ModelAttribute("recordCreate") ActivityRecordCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "record/new";
        }
        //create record
        ActivityRecordDTO recordDTO = new ActivityRecordDTO();
        recordDTO.setActivity(activityFacade.findActivityById(formBean.getActivityId()));
        recordDTO.setUser(userFacade.getUserById(formBean.getUserId()));
        recordDTO.setDate(formBean.getDate());
        recordDTO.setDistance(formBean.getDistance());
        recordDTO.setDuration(formBean.getDuration());

//        TODO: Create create GUI

        Long id = recordFacade.create(recordDTO);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Record " + id + " was created");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }
}
