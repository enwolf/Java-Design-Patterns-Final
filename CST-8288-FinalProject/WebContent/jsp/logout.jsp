<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logout.css"> 
</head>
<body>
    <div class="container">
        <header>
            <h1>Logout Successful</h1>
        </header>
        <section class="logout-container">
            <h2>You have been logged out.</h2>
            <p>You will be redirected to the home page in <span id="countdown" class="countdown-number">5</span> seconds.</p>
            <p>If you are not redirected, <a href="${pageContext.request.contextPath}/index.jsp">click here</a>.</p>
        </section>
        <footer>
            <p>© 2024 Food Waste Reduction Platform. All rights reserved.</p>
        </footer>
    </div>

    <script>
        // JavaScript code for countdown timer
        var countdownElement = document.getElementById("countdown");
        var count = 5; 
        
        // Update countdown every second
        var countdownInterval = setInterval(function() {
            count--;
            countdownElement.textContent = count;
            
            // Redirect when count reaches 0
            if (count <= 0) 
            {
                clearInterval(countdownInterval); 
                window.location.href = "${pageContext.request.contextPath}/index.jsp"; // Redirect
            }
        }, 1000); // 1000 milliseconds = 1 second
    </script>
</body>
</html>
