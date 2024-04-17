<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Success</title>
    <link rel="stylesheet" href="../css/registrationSuccess.css"> <!-- Path to your CSS file -->
</head>
<body>
    <div class="container">
        <h1>Registration Successful!</h1>
        <p>Congratulations! You have been registered successfully.</p>
        
        <!-- Display user details -->
        <h3>Your Registration Details:</h3>
        <ul>
            <li>First Name: <%= request.getSession().getAttribute("firstName") %></li>
            <li>Last Name: <%= request.getSession().getAttribute("lastName") %></li>
            <li>Email: <%= request.getSession().getAttribute("email") %></li>
            <li>User Type: <%= request.getSession().getAttribute("userType") %></li>
            <li>Contact Method: <%= request.getSession().getAttribute("contactMethod") %></li>
            <li>Contact Info: <%= request.getSession().getAttribute("contactInfo") %></li>
        </ul>

        <p>Your registration details have been sent to your email. Please verify your email to complete the registration process.</p>
        <a href="login.jsp">Click here to login</a> 
    </div>
</body>
</html>
