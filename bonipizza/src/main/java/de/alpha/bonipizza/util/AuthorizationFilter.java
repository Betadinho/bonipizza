package de.alpha.bonipizza.util;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {}

    private static final String AJAX_REDIRECT_XML = "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\"\n" +
            "        + \"<partial-response><redirect url=\\\"%s\\\"></redirect></partial-response>";


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpSession session = request.getSession(false);
            String loginURL = request.getContextPath() + "/login.xhtml";

            boolean loggedIn = (session != null) && (session.getAttribute("username") != null);
            boolean loginRequest = request.getRequestURI().equals(loginURL);
            boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
            boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces Request"));

            if(loggedIn || loginRequest || resourceRequest) {
                if(!resourceRequest) {
                    response.setHeader("Cache Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                } chain.doFilter(request, response);
            } else if (ajaxRequest) {
                response.setContentType("text/xml");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().printf(AJAX_REDIRECT_XML, loginURL);
            } else response.sendRedirect(loginURL);
        }

@Override
public void destroy() {}
}
