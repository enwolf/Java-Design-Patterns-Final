package org.cst8288.finalproject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet  extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Invalidate the session to clear all session attributes and log out the user
        request.getSession().invalidate();

        // Redirect to the logout confirmation page
        response.sendRedirect("/jsp/logout.jsp");
    }

}
