package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 * Created by michal on 1/16/16.
 */
@Controller
@RequestMapping("/secured")
public class AuthController {

    final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authForm(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        log.debug("login called");

        if (req.getSession().getAttribute("sessionId") != null) {
//          if sessionId is contained in the session, forward the request to the authenticated part
            return "redirect:/secured/activity/list";
        }
        // else display login form

        return "secured/login";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authenticate(@RequestParam String userId, @RequestParam String password, Model model,
                               RedirectAttributes redirectAttributes, HttpServletRequest req, HttpServletResponse res) {

        UserDTO userDTO = userFacade.getUserByEmail(userId);
        if(userDTO == null){
            redirectAttributes.addFlashAttribute("alert_info", "User with email "+userId+" does not exist here");
            return "redirect:/secured/login";
        }

        //authentication:
        UserAuthenticateDTO authDTO = new UserAuthenticateDTO();
        authDTO.setId(userDTO.getId());
        authDTO.setUserId(userId);
        authDTO.setPassword(password);

        if(userFacade.authenticate(authDTO)){
            //sets new sessionId into onward session.
            req.getSession().setAttribute("sessionId", userId+":"+userFacade.getUserSession(userFacade.getUserByEmail(userId)));
            return "redirect:/secured/activity/list";
        }

        redirectAttributes.addFlashAttribute("alert_warning", "Wrong credentials. Please check");
        return "redirect:/secured/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest req, HttpServletResponse res) {
        req.getSession().removeAttribute("sessionId");

        redirectAttributes.addFlashAttribute("alert_info", "You have been successfully logged out.");
        return "redirect:/secured/login";
    }
}
