package org.cst8288.finalproject.servlets;

import java.io.IOException;

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
import org.cst8288.finalproject.users.User;
import org.cst8288.finalproject.validator.UserValidator;

/**
 * Servlet implementation to handle profile editing functionality.
 * This servlet manages two main operations:
 * - **GET Request**: Display the edit profile page to the user.
 * - **POST Request**: Process the submitted profile data, update the user's profile, and handle validation and persistence.
 * 
 * Only logged-in users can view or modify their profile. If a user session does not exist or is invalid, the servlet
 * redirects to the login page.
 * 
 * This servlet uses {@link UserManager} for user operations which encapsulates all business logic for user management
 * including validation and CRUD operations on user data.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see UserManager
 * @see ManageUserDAO
 * @see UserAuthenticationDAO
 * @see UserValidator
 */
public class EditProfileServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
    private static final LMSLogger LOGGER = LMSLogger.getInstance();
    private final String CLASS_NAME = getClass().getSimpleName();  // Class name variable

    /**
     * Handles the HTTP GET request.
     * This method checks for a valid user session and forwards to the edit profile page.
     * If no valid session is found, it redirects the user to the login page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) 
        {
            LOGGER.warn(CLASS_NAME + ": Session not found or no user attribute in session.");
            response.sendRedirect("/jsp/login.jsp");
            return;
        }
        LOGGER.debug(CLASS_NAME + ": Forwarding to editProfile.jsp");
        request.getRequestDispatcher(request.getContextPath() + "/editProfile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST request.
     * This method processes the form submission from the edit profile page, validates and updates the user's profile data.
     * After updating, it sets a success or error message based on the result and redirects back to the profile page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the POST request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        ManageUserDAO manageUserDAO = new ManageUserDAO(new UserDataExtractorService());
        UserValidator userValidator = new UserValidator();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) 
        {
            LOGGER.warn(CLASS_NAME + ": Invalid session or user not found.");
            response.sendRedirect("/jsp/login.jsp");
            return;
        }

        AbstractUser user = (User) session.getAttribute("user");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isUpdated = false;

        if (firstName != null && !firstName.isEmpty()) 
        {
            user.setUserFirstName(firstName);
            LOGGER.debug(CLASS_NAME + ": Updated firstName to " + firstName);
            isUpdated = true;
        }
        
        if (lastName != null && !lastName.isEmpty()) 
        {
            user.setUserLastName(lastName);
            LOGGER.debug(CLASS_NAME + ": Updated lastName to " + lastName);
            isUpdated = true;
        }
        
        if (email != null && !email.isEmpty()) 
        {
            user.setEmailAddress(email);
            LOGGER.debug(CLASS_NAME + ": Updated email to " + email);
            isUpdated = true;
        }
        
        if (password != null && !password.isEmpty()) 
        {
            ((User)user).setPassword(password);  // Ensure password hashing
            LOGGER.debug(CLASS_NAME + ": Updated password.");
            isUpdated = true;
        }

        if (!isUpdated) 
        {
            LOGGER.info(CLASS_NAME + ": No updates were made as no fields were filled out.");
            request.setAttribute("errorMessage", "No updates were made as no fields were filled out.");
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
            return;
        }

        UserManager userManager = new UserManager(manageUserDAO, userValidator, new UserAuthenticationDAO());
        boolean updateStatus = userManager.updateUser(user);

        if (updateStatus) 
        {
            session.setAttribute("user", user);
            LOGGER.info(CLASS_NAME + ": Profile updated successfully.");
            request.setAttribute("successMessage", "Profile updated successfully!");
        }
        else 
        {
            LOGGER.error(CLASS_NAME + ": Failed to update profile.");
            request.setAttribute("errorMessage", "Failed to update profile.");
        }
        request.getRequestDispatcher("/jsp/editProfile.jsp").forward(request, response);
    }
}
