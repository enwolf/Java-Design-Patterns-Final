package org.cst8288.finalproject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for managing user logout operations.
 * This servlet handles the logout process by invalidating the current HTTP session
 * and redirecting the user to a logout confirmation page. It ensures that any session
 * data is cleaned up properly to prevent unauthorized access after logout.
 *  
 * See also related classes and interfaces for comprehensive understanding of the 
 * session management and authentication framework.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * 
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.http.HttpServletRequest
 * @see javax.servlet.http.HttpServletResponse
 * @see javax.servlet.http.HttpSession
 */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request by invalidating the existing session and redirecting
     * the user to the logout page.
     * 
     * This method ensures that the user's session is terminated effectively, removing
     * all session attributes and ensuring the user must re-authenticate to access secured
     * resources. This is a critical step in securing the application against session hijacking.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Attempt to get an existing session without creating a new one
        HttpSession session = request.getSession(false);
        
        // Check if a session exists
        if (session != null) {
            // Invalidate the session to log the user out
            session.invalidate();
        }
        
        // Redirect to the logout confirmation page
        response.sendRedirect(request.getContextPath() + "/jsp/logout.jsp");
    }
}
