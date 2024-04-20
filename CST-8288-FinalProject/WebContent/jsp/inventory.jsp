<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="org.cst8288.finalproject.logger.LMSLogger"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Current Inventory</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/inventory.css">
</head>
<body>
	<%
	LMSLogger logger = LMSLogger.getInstance();
	logger.debug("Test log from JSP page");
	%>
	<div class="container">
		<h1>Inventory List</h1>
		<table>
			<thead>
				<tr>
					<th>Item Name</th>
					<th>Quantity</th>
					<th>Expiration Date</th>
					<th>Price</th>
				</tr>
			</thead>
       			<jsp:include page="/includes/inventoryTableBody.jsp" />	 
		</table>
	</div>
</body>
</html>
