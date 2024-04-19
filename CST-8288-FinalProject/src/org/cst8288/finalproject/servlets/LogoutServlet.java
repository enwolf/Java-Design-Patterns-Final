package org.cst8288.finalproject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Attempt to get an existing session without creating a new one
        HttpSession session = request.getSession(false);
        
        // Check if a session exists
        if (session != null) {
            // Invalidate the session to log the user out
            session.invalidate();
        }
        

        response.sendRedirect(request.getContextPath() + "/jsp/logout.jsp");
    }
}
