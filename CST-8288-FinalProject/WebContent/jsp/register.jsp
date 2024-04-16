<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Result</title>
</head>
<body>
    <h1>Registration Result</h1>
    <% 
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String contactMethod = request.getParameter("contactMethod");
        String contactInfo = request.getParameter("contactInfo");

        // You can process the registration data here (e.g., store in database, send confirmation email, etc.)

        out.println("<p>First Name: " + firstName + "</p>");
        out.println("<p>Last Name: " + lastName + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<p>User Type: " + userType + "</p>");
        
        if (contactMethod != null && !contactMethod.isEmpty()) {
            out.println("<p>Contact Method: " + contactMethod + "</p>");
            out.println("<p>Contact Info: " + contactInfo + "</p>");
        }
    %>
</body>
</html>
