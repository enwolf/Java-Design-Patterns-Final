<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
</head>
<body>
    <h1>Sign Up</h1>
    <form action="register.jsp" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required><br><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="userType">User Type:</label>
        <select id="userType" name="userType">
            <option value="Consumer">Consumer</option>
            <option value="Retailer">Retailer</option>
            <option value="Charitable Organization">Charitable Organization</option>
        </select><br><br>

        <input type="checkbox" id="contactMethodCheckbox" name="contactMethodCheckbox">
        <label for="contactMethodCheckbox">Preferred Contact Method:</label>
        <select id="contactMethod" name="contactMethod" disabled>
            <option value="Email">Email</option>
            <option value="Phone">Phone</option>
        </select><br><br>

        <label for="contactInfo">Contact Information:</label>
        <input type="text" id="contactInfo" name="contactInfo" disabled><br><br>

        <input type="submit" value="Sign Up">
    </form>

    <script>
        // Enable/disable contact method and contact info based on checkbox
        const contactMethodCheckbox = document.getElementById('contactMethodCheckbox');
        const contactMethodSelect = document.getElementById('contactMethod');
        const contactInfoInput = document.getElementById('contactInfo');

        contactMethodCheckbox.addEventListener('change', () => {
            if (contactMethodCheckbox.checked) {
                contactMethodSelect.disabled = false;
                contactInfoInput.disabled = false;
            } else {
                contactMethodSelect.disabled = true;
                contactInfoInput.disabled = true;
            }
        });
    </script>
</body>
</html>

