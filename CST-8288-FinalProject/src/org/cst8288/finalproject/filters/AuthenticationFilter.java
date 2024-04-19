package org.cst8288.finalproject.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the session
        HttpSession session = httpRequest.getSession(false);

        // Check if the session exists and if the user is authenticated
        if (session == null || session.getAttribute("user") == null) {
            // If the user is not authenticated, redirect to the login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/jsp/login.jsp");
            return;
        }

        // If the user is authenticated, proceed with the request
        filter.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }
}
