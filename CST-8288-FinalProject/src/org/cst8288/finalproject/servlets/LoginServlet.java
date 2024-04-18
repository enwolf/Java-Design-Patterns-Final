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
import org.cst8288.finalproject.manager.UserPasswordManager;
import org.cst8288.finalproject.users.AbstractUser;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserPasswordManager userPasswordManager;
    private ManageUserDAO manageUserDAO;
    private LMSLogger logger = LMSLogger.getInstance();

    public void init() 
    {
        UserAuthenticationDAO userAuthDAO = new UserAuthenticationDAO();
        this.userPasswordManager = new UserPasswordManager(userAuthDAO);
        this.manageUserDAO = new ManageUserDAO();
        logger.debug("LoginServlet initialized with UserPasswordManager and ManageUserDAO.");
        
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

        boolean isValidUser = userPasswordManager.verifyPassword(email, password);

        if (isValidUser) 
        {
            logger.info("Login successful for email: " + email);
        
            AbstractUser user = manageUserDAO.returnUserByEmail(email);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
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
