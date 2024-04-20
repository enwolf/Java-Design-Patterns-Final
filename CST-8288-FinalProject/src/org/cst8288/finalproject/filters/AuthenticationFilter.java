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

/**
 * Servlet Filter implementation class for authentication.
 * This filter ensures that users are logged in before allowing access to certain protected resources.
 * It intercepts requests to specific pages (dashboard, profile editing, and inventory addition) and checks if there is a valid user session.
 * If a user session is not found, it redirects the user to the login page, effectively preventing unauthorized access to
 * these protected pages.
 *
 * This mechanism is crucial for maintaining the security and integrity of user-specific data and functions within the application.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see javax.servlet.Filter
 */
@WebFilter(urlPatterns = { "/jsp/userDashboard.jsp", "/jsp/editProfile.jsp", "/jsp/addItemToInventory.jsp"}) 
public class AuthenticationFilter implements Filter {

	/**
	 * This method implements a filter that checks if a user is logged in.
	 * If a session exists and contains the "user" attribute, the request is allowed to proceed.
	 * Otherwise, the user is redirected to the login page.
	 * 
	 * @param request The servlet request object.
	 * @param response The servlet response object.
	 * @param filter The filter chain for invoking subsequent filters in the chain.
	 * @throws IOException If an I/O exception occurs while processing the request or response.
	 * @throws ServletException If a servlet exception occurs while processing the request or response.
	 */
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
