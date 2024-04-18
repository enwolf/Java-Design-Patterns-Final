<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="../css/login.css"> 
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="POST"> 
            <div class="form-group">
                <label for="email">Email Address:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit" class="login-button">Log In</button>
            </div>
            <div class="links">
                <a href="forgot-password">Forgot Password?</a> 
                <a href="register.jsp">Need an account? Sign Up</a>
            </div>
        </form>
    </div>
</body>
</html>
