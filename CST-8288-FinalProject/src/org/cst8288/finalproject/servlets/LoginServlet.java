package org.cst8288.finalproject.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cst8288.finalproject.dao.ManageUserDAO;
import org.cst8288.finalproject.dao.UserAuthenticationDAO;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.manager.UserManager;
import org.cst8288.finalproject.service.UserDataExtractorService;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.validator.UserValidator;

/**
 * Servlet implementation for handling login requests.
 * This servlet handles both displaying the login page and processing login attempts. It uses the {@link UserManager}
 * to authenticate users and manages user sessions by storing user details and redirects users based on their role
 * to the appropriate dashboard.
 * 
 * It ensures that all login attempts are logged for security auditing and provides detailed logs for debugging purposes.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-18
 * @see UserManager
 * @see HttpServletRequest
 * @see HttpServletResponse
 * @see LMSLogger
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserManager userManager;
    private LMSLogger logger = LMSLogger.getInstance();

    /**
     * Initializes the servlet and creates an instance of {@link UserManager} that handles all user authentication,
     * user details retrieval and role-based redirection.
     */
    public void init() 
    {
        userManager = new UserManager(new ManageUserDAO(new UserDataExtractorService()), new UserValidator(), new UserAuthenticationDAO());
    }

    /**
     * Handles the HTTP GET request by forwarding to the login page.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        logger.debug("Forwarding to login page.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP POST requests by processing login attempts. It authenticates user credentials
     * and, upon successful authentication, stores user details in the session and redirects to the appropriate
     * dashboard based on user type (Retailer, Consumer, Charitable Organization). If authentication fails,
     * it redirects back to the login page with an error message.
     *
     * Detailed user type handling and redirection logic are implemented to ensure proper access control and
     * user experience.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.debug("Context Path: " + request.getContextPath());
        logger.debug("Attempting login for email: " + email);

        boolean isValidUser = userManager.authenticateUserPassword(email, password);

        if (isValidUser) 
        {
            logger.info("Login successful for email: " + email);
        
            AbstractUser user = userManager.getUserByEmail(email);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Handle different user types
            switch(user.getUserType()) 
            {
                case RETAILER:
                    Retailer retailerDetails = (Retailer) userManager.populateUserDetails(user);
                    session.setAttribute("retailerDetails", retailerDetails);
                    break;
                case CONSUMER:
                    Consumer consumerDetails = (Consumer) userManager.populateUserDetails(user);
                    session.setAttribute("consumerDetails", consumerDetails);
                    break;
                case CHARITABLE_ORGANIZATION:
                    CharitableOrganization charityDetails = (CharitableOrganization) userManager.populateUserDetails(user);
                    session.setAttribute("charityDetails", charityDetails);
                    break;
                default:
                    logger.debug("Standard user details stored in session.");
                    break;
            }
            logger.debug("User details added to session. Redirecting to user dashboard.");
            response.sendRedirect(request.getContextPath() + "/jsp/userDashboard.jsp");
        } 
        else 
        {
            logger.warn("Login attempt failed for email: " + email + ". Invalid email or password.");
            
            request.setAttribute("errorMessage", "Invalid email or password!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
