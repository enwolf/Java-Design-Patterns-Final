<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" href="../css/register.css">
    <script>
		function updateUserTypeFields() 
		{
	    
	    // Retrieve the selected user type from the dropdown menu
    	var userType = document.getElementById("userType").value;

	    // Define the fields for each user type. These are the divs that contain specific input
        // fields for each type of user (Retailer, Consumer, Charitable Organization)
    	var fields = {
        	'retailer': document.getElementById("retailerFields"),
        	'consumer': document.getElementById("consumerFields"),
        	'charitable_organization': document.getElementById("charitableFields")
    	};

    	// This loop will hide all user type-specific fields first and remove the 'required' attribute
    	// from all input and select elements within these fields. This is important to prevent
    	// validation issues with hidden fields when the form is submitted.
    	Object.values(fields).forEach(field => {
        	field.style.display = 'none';  // Hide the field
        	// Find all input and select elements within the current field and remove the 'required' attribute
        	Array.from(field.querySelectorAll("input, select")).forEach(input => {
            	input.required = false;
        	});
    	});

	    // If a valid user type is selected and it has corresponding fields defined,
	    // show these fields and set their inputs as required.
    	if (userType && fields[userType]) 
    	{
        	fields[userType].style.display = 'block'; // Show the relevant field section
	        // For all input and select elements in the now visible field section, set them as required
    	    // This ensures that the user cannot submit the form without filling these out.
	        Array.from(fields[userType].querySelectorAll("input, select")).forEach(input => {
    	        input.required = true;
        });
    }
}
</script>

</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="${pageContext.request.contextPath}/register" method="POST">
            <input type="text" name="firstName" placeholder="First Name" required>
            <input type="text" name="lastName" placeholder="Last Name" required>
            <input type="email" name="email" placeholder="Email Address" required>
            <input type="password" name="password" placeholder="Password" required>

            <select id="userType" name="userType" required onchange="updateUserTypeFields()">
                <option value="">Select User Type</option>
                <option value="retailer">Retailer</option>
                <option value="consumer">Consumer</option>
                <option value="charitable_organization">Charitable Organization</option>
            </select>

            <div id="retailerFields" style="display:none">
                <jsp:include page="/includes/retailerRegister.jsp" />
                <jsp:include page="/includes/registerButton.jsp" />
            </div>

            <div id="consumerFields" style="display:none">
                <jsp:include page="/includes/consumerRegister.jsp" />
                <jsp:include page="/includes/registerButton.jsp" />
            </div>

            <div id="charitableFields" style="display:none">
                <jsp:include page="/includes/charitableRegister.jsp" />
                <jsp:include page="/includes/registerButton.jsp" />
            </div>

        </form>
    </div>
 <script>
 		document.querySelector('.button').addEventListener('click', (event) => {
	    console.log('Button clicked');
	    console.log('Form action:', event.target.form.action);
	    event.target.form.submit();  // Force form submission for testing
	});
</script>
</body>
</html>
