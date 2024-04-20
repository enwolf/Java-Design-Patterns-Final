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

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserManager userManager;

    private LMSLogger logger = LMSLogger.getInstance();

    public void init() {
        userManager = new UserManager(new ManageUserDAO(new UserDataExtractorService()), new UserValidator(), new UserAuthenticationDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        logger.debug("Forwarding to login page.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

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
