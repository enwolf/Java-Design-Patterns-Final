<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page</title>
<link rel="stylesheet" href="../css/register.css">
</head>
<body>
	<div class="container">
		<h2>Register</h2>
		<form action="${pageContext.request.contextPath}/register" method="POST">
			<input type="text" name="firstName" placeholder="First Name" required>
			<input type="text" name="lastName" placeholder="Last Name" required>
			<input type="email" name="email" placeholder="Email Address" required>
			<input type="password" name="password" placeholder="Password"
				required> <select name="userType" required>
				<option value="">Select User Type</option>
				<option value="retailer">Retailer</option>
				<option value="consumer">Consumer</option>
				<option value="charitable_organization">Charitable Organization</option>
			</select> <select name="contactMethod">
				<option value="">Preferred Contact Method</option>
				<option value="email">Email</option>
				<option value="phone">Phone</option>
			</select> <input type="text" name="contactInfo"
				placeholder="Contact Info (Optional)">
			<button type="submit">Register</button>
		</form>
	</div>
</body>
</html>
