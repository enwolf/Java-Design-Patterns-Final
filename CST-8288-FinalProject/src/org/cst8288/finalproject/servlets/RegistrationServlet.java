package org.cst8288.finalproject.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.userdao.ManageUserDAO;
import org.cst8288.finalproject.userdao.RegisterUserManager;
import org.cst8288.finalproject.users.User;
import org.cst8288.finalproject.validator.UserValidator;


/**
 * This servlet handles user registration by interfacing between the user and the application's backend.
 * It processes GET requests by directing users to the registration page and handles POST requests
 * by creating and persisting user registration details. Upon successful registration, users are redirected
 * to a success page. The servlet logs registration errors to the console for debugging purposes.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-12
 * @see HttpServlet
 * @see HttpServletRequest
 * @see HttpServletResponse
 * @see RequestDispatcher
 * @see HttpSession
 * @see org.cst8288.finalproject.enums.UserType
 * @see org.cst8288.finalproject.userdao.ManageUserDAO
 * @see org.cst8288.finalproject.userdao.RegisterUserManager
 * @see org.cst8288.finalproject.users.User
 * @see org.cst8288.finalproject.validator.UserValidator
 */
public class RegistrationServlet extends HttpServlet{

	
	//The IDE ask me for this and it made an error go away so I left it. 
	 
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP GET request by directing clients to the registration view.
	 * This method is called when a user navigates to the registration page of the application.
	 * It forwards the request to the 'register.jsp' page, which contains the form
	 * that users fill out to register. The method ensures that the user sees the
	 * fresh form every time the registration URL is accessed, preventing form resubmission
	 * issues and displaying an up-to-date view.
	 *
	 * The forwarding is handled internally on the server without changing the URL
	 * in the user's browser, providing a smoother experience to the user. If there
	 * are any issues in accessing or forwarding to the 'register.jsp' page,
	 * the method will handle the exceptions by throwing them to be caught by
	 * the container's error-handling machinery.
	 *
	 * @param req The HttpServletRequest object that encapsulates all information
	 *            about the request made by the client. This includes parameters,
	 *            headers, and any other request-related data.
	 * 
	 * @param resp The HttpServletResponse object through which the servlet can
	 *             respond to the client. While this method does not directly write
	 *             to the response object, it uses it to obtain a RequestDispatcher
	 *             which can forward the request/response to the JSP.
	 * 
	 * @throws ServletException 
	 * @throws IOException      
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // Forwards to the registration form
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/register.jsp");
	    dispatcher.forward(req, resp);
	}
	
	
	/**
	 * Handles the HTTP POST request.
	 * This method processes the registration form submission, creates a new User object,
	 * validates it, and if validation is successful, adds the user to the database.
	 * On successful registration, it redirects the user to the success page.
	 *
	 * @param request The HttpServletRequest object that contains the request the client made of the servlet.
	 * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
	 * @throws ServletException if the request for the POST could not be handled.
	 * @throws IOException if an input or output error occurs while the servlet is handling the POST request.
	 */
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
		 User user = new User();
		 ManageUserDAO manageUserDAO = new ManageUserDAO();
		 UserValidator userValidator = new UserValidator();
		 RegisterUserManager registerUserManager = new RegisterUserManager(manageUserDAO, userValidator);
		 
		 String contactMethod;
		 String contactInfo;
			
		 user.setUserFirstName(request.getParameter("firstName"));
		 user.setUserFirstName(request.getParameter("firstName"));
		 user.setUserLastName(request.getParameter("lastName"));
		 user.setEmailAddress(request.getParameter("email"));
		 user.setPassword(request.getParameter("password"));
		 user.setUserType(UserType.valueOf(request.getParameter("userType").toUpperCase()));
	     
		 contactMethod = request.getParameter("contactMethod");
	     contactInfo = request.getParameter("contactInfo");
	     
	     registerUserManager.registerUser(user);	     
	     System.out.println(user.toString());

	     // Store user details in session for use on the success page
	     HttpSession session = request.getSession();
	     session.setAttribute("firstName", user.getUserFirstName());
	     session.setAttribute("lastName", user.getUserLastName());
	     session.setAttribute("email", user.getEmailAddress());
	     session.setAttribute("userType", user.getUserType().toString());
	     session.setAttribute("contactMethod", contactMethod); 
	     
	     response.sendRedirect(request.getContextPath() + "/jsp/registrationSuccess.jsp");
	     
	 }
}
