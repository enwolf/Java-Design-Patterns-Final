package org.cst8288.finalproject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cst8288.finalproject.dao.ManageUserDAO;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.manager.UserManager;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;
import org.cst8288.finalproject.validator.UserValidator;

public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final LMSLogger LOGGER = LMSLogger.getInstance();
    private final String CLASS_NAME = getClass().getSimpleName();  // Class name variable


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) 
        {
            LOGGER.warn(CLASS_NAME + ": Session not found or no user attribute in session.");
            response.sendRedirect("login.jsp");
            return;
        }
        LOGGER.debug(CLASS_NAME + ": Forwarding to editProfile.jsp");
        request.getRequestDispatcher(request.getContextPath() + "/editProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManageUserDAO manageUserDAO = new ManageUserDAO();
        UserValidator userValidator = new UserValidator();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            LOGGER.warn(CLASS_NAME + ": Invalid session or user not found.");
            response.sendRedirect("login.jsp");
            return;
        }

        AbstractUser user = (User) session.getAttribute("user");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isUpdated = false;

        if (firstName != null && !firstName.isEmpty()) {
            user.setUserFirstName(firstName);
            LOGGER.debug(CLASS_NAME + ": Updated firstName to " + firstName);
            isUpdated = true;
        }
        if (lastName != null && !lastName.isEmpty()) {
            user.setUserLastName(lastName);
            LOGGER.debug(CLASS_NAME + ": Updated lastName to " + lastName);
            isUpdated = true;
        }
        if (email != null && !email.isEmpty()) {
            user.setEmailAddress(email);
            LOGGER.debug(CLASS_NAME + ": Updated email to " + email);
            isUpdated = true;
        }
        if (password != null && !password.isEmpty()) {
            ((User)user).setPassword(password);  // Ensure password hashing
            LOGGER.debug(CLASS_NAME + ": Updated password.");
            isUpdated = true;
        }

        if (!isUpdated) {
            LOGGER.info(CLASS_NAME + ": No updates were made as no fields were filled out.");
            request.setAttribute("errorMessage", "No updates were made as no fields were filled out.");
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
            return;
        }

        UserManager userManager = new UserManager(manageUserDAO, userValidator);
        boolean updateStatus = userManager.updateUser(user);

        if (updateStatus) {
            session.setAttribute("user", user);
            LOGGER.info(CLASS_NAME + ": Profile updated successfully.");
            request.setAttribute("successMessage", "Profile updated successfully!");
        } else {
            LOGGER.error(CLASS_NAME + ": Failed to update profile.");
            request.setAttribute("errorMessage", "Failed to update profile.");
        }
        request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
    }
}
