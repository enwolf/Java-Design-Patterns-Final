<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProfile.css"> <!-- Adjusted the path to ensure CSS is correctly linked -->
</head>
<body>
    <div class="container">
        <header>
            <h1>Edit Your Profile</h1>            
        </header>
        <form action="${pageContext.request.contextPath}/editProfile" method="post" class="profile-form"> <!-- Adjusted action path -->
            <div class="form-group">
                <div class="label-wrapper">
                    <label class="form-label">User Type:</label>
                </div>
                <div class="input-wrapper">
                    <span class="user-type">${user.userType}</span> <!-- Added class for potential styling -->
                </div>
            </div>
            <div class="form-group">
                <div class="label-wrapper">
                    <label for="firstName" class="form-label">First Name:</label>
                </div>
                <div class="input-wrapper">
                    <input type="text" id="firstName" name="firstName" value="${user.userFirstName}" required class="form-input">
                </div>
            </div>
            <div class="form-group">
                <div class="label-wrapper">
                    <label for="lastName" class="form-label">Last Name:</label>
                </div>
                <div class="input-wrapper">
                    <input type="text" id="lastName" name="lastName" value="${user.userLastName}" required class="form-input">
                </div>
            </div>
            <div class="form-group">
                <div class="label-wrapper">
                    <label for="email" class="form-label">Email:</label>
                </div>
                <div class="input-wrapper">
                    <input type="email" id="email" name="email" value="${user.emailAddress}" required class="form-input">
                </div>
            </div>
            <div class="form-group">
                <div class="label-wrapper">
                    <label for="password" class="form-label">Update Password:</label>
                </div>
                <div class="input-wrapper">
                    <input type="password" id="password" name="password" placeholder="Enter new password"  class="form-input">
                </div>
            </div>
            <div class="form-buttons">
                <button type="submit" class="button">Update Profile</button>
                <a href="${pageContext.request.contextPath}/jsp/userDashboard.jsp" class="button">Back to Dashboard</a> <!-- Moved link inside form-buttons for layout -->
            </div>
        </form>
    </div>
</body>
</html>
