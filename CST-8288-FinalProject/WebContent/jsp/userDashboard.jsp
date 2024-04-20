<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="../css/userDashboard.css">
    <script>
    function displayUserSpecificInfo() {
        var userType = document.getElementById("userTypeValue").value;
        var sections = {
            'RETAILER': document.getElementById("retailerInfo"),
            'CONSUMER': document.getElementById("consumerInfo"),
            'CHARITABLE_ORGANIZATION': document.getElementById("charityInfo")
        };

        // Hide all sections first
        Object.values(sections).forEach(section => section.style.display = 'none');

        // Show the relevant section based on the user type
        if (sections[userType]) {
            sections[userType].style.display = 'block';
        }
    }

    window.onload = displayUserSpecificInfo; // Run function on page load to ensure the correct section is shown
    </script>
</head>
<body>
    <div class="container">
        <header>
            <h1>Welcome to Your Dashboard, ${user.userFirstName} ${user.userLastName}</h1>
            <input type="hidden" id="userTypeValue" value="${user.userType}">
        </header>
        <section>
            <h2>Profile Details</h2>
            <div class="profile-details">
                <p><strong>User Type:</strong> ${user.userType}</p>
                <p><strong>First Name:</strong> ${user.userFirstName}</p>
                <p><strong>Last Name:</strong> ${user.userLastName}</p>
                <p><strong>Email:</strong> ${user.emailAddress}</p>

            </div>
        </section>
        <!-- Consumer Specific Info -->
        <div class="profile-details" id="consumerInfo" style="display:none">
            <h3>Consumer Information</h3>
            <p><strong>Account Balance:</strong> ${consumerDetails.accountBalance}</p>
            <p><strong>Street Address:</strong> ${consumerDetails.streetAddress}</p>
            <p><strong>City:</strong> ${consumerDetails.city}</p>
            <p><strong>Province:</strong> ${consumerDetails.province}</p>
            <p><strong>Postal Code:</strong> ${consumerDetails.postalCode}</p>
        </div>
       <!-- Retailer Specific Info -->
        
        <div class="profile-details" id="retailerInfo" style="display:none">
        	<h3>Retailer Information</h3>    
            <p><strong>Store Name:</strong> ${retailerDetails.storeName}</p>
            <p><strong>Street Address:</strong> ${retailerDetails.streetAddress}</p>
            <p><strong>City:</strong> ${retailerDetails.city}</p>
            <p><strong>Province:</strong> ${retailerDetails.province}</p>
            <p><strong>Postal Code:</strong> ${retailerDetails.postalCode}</p>
            <!-- Add Item to Inventory Button -->
            <form action="${pageContext.request.contextPath}/jsp/addItemToInventory.jsp" method="post">
                <input type="hidden" name="userId" value="${user.userId}">
                <button type="submit" class="button">Add Item to Inventory</button>
            </form>
        </div>
        <!-- Charitable Organization Specific Info -->
        <div class="profile-details" id="charityInfo" style="display:none">
            <h3>Charitable Organization Information</h3>
            <p><strong>Organization Name:</strong> ${charityDetails.organizationName}</p>
            <p><strong>Street Address:</strong> ${charityDetails.streetAddress}</p>
            <p><strong>City:</strong> ${charityDetails.city}</p>
            <p><strong>Province:</strong> ${charityDetails.province}</p>
            <p><strong>Postal Code:</strong> ${charityDetails.postalCode}</p>
        </div>
		<!-- Edit Profile Form -->
        <form action="${pageContext.request.contextPath}/jsp/editProfile.jsp" method="post" class="profile-form">
	        <input type="hidden" name="userId" value="${user.userId}">
	        <button type="submit" class="button">Edit Profile</button>
        </form>
        <footer>
            <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a>
        </footer>
    </div>
</body>
</html>
