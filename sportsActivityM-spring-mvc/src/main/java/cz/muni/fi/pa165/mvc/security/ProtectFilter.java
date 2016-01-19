package cz.muni.fi.pa165.mvc.security;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.UserState;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * Protects administrative part of application.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@WebFilter(urlPatterns = {"/secured/auth/*"})
public class ProtectFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        //get Spring context and UserFacade from it
        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(UserFacade.class);

        HttpSession session = request.getSession(true);

        if(session.getAttribute("sessionId") == null){
            response401(session, response);
            return;
        }

        String sessionId = (String)session.getAttribute("sessionId");
        String userId = sessionId.split(":")[0];

        log.debug("Attempt to authorize with userId: "+userId);

        UserDTO matchingUser = userFacade.getUserByEmail(userId);

        //if user's sessionId matches user's hash:
        if (userFacade.getUserSession(matchingUser).equals(sessionId.split(":")[1])) {
            log.debug("user with email {} passed protectFilter", userId);
            request.setAttribute("authenticatedUser", matchingUser);

            chain.doFilter(request, response);
            return;
        } else {
            log.debug("wrong sessionId: "+sessionId);
            log.debug("matching user email and hash: "+matchingUser.getEmail()+userFacade.getUserSession(matchingUser));
        }

        session.setAttribute("loginStatus", true);

    }

    private void response401(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("loginStatus", false);

        response.sendRedirect("/pa165/secured/login");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
