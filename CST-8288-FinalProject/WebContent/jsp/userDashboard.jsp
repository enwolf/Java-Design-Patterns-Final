<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="../css/userDashboard.css"> <!-- Ensure the path to your CSS file is correct -->
</head>
<body>
    <div class="container">
        <header>
            <h1>Welcome to Your Account Dashboard: ${user.emailAddress}</h1> <!-- Displays the user's full name -->
        </header>
        <section>
            <h2>Profile: ${user.userFirstName} ${user.userLastName}</h2>
            <div class="profile-details">
                <p><strong>User Type:</strong> ${user.userType}</p>
                <p><strong>First Name:</strong> ${user.userFirstName}</p>
                <p><strong>Last Name:</strong> ${user.userLastName}</p>
                <p><strong>Email:</strong> ${user.emailAddress}</p>
                
            </div>
            <form action="${pageContext.request.contextPath}/jsp/editProfile.jsp" method="post" class="profile-form">
                <input type="hidden" name="userId" value="${user.userId}">
                <button type="submit" class="button">Edit Profile</button>
            </form>
        </section>
        <footer>
            <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a> 
        </footer>
    </div>
</body>
</html>
