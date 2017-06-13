package de.alpha.bonipizza.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by prechtla on 02.06.17.
 */
public class SessionUtils {
    public static HttpSession getSession() {
        return (HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUsername() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public String getUserId() {
        HttpSession session = getSession();
        return (String)session.getAttribute("userid");
    }
}
