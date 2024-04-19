package org.cst8288.finalproject.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/jsp/userDashboard.jsp", "/jsp/editProfile.jsp" }) 
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Get the existing session
        HttpSession session = httpRequest.getSession(false);

        // Check if a session exists and if it contains the "user" attribute
        if (session != null && session.getAttribute("user") != null) 
            // User is logged in, so just continue the request.
            filter.doFilter(request, response);
        else 
            // No user is logged in, redirect to the login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/jsp/login.jsp");        
    }
}
