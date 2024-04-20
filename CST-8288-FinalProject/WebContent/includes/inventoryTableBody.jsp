<%@ page import="java.util.List"%>
<%@ page import="org.cst8288.finalproject.dto.FoodItem"%>
<tbody>
	<%
		List<FoodItem> foodItems = (List<FoodItem>) request.getAttribute("inventoryItems");
		if (foodItems == null) 
		{
	%>
			<tr>
				<td colspan="4">Error: Inventory items not available.</td>
			</tr>
	<%
		} 
		else if (foodItems.isEmpty()) 
		{
	%>
			<tr>
				<td colspan="4">No inventory items available.</td>
			</tr>
	<%
	} 
		else 
		{
			for (FoodItem item : foodItems) 
			{
			%>
			<tr>
				<td><%=item.getItemName()%></td>
				<td><%=item.getQuantity()%></td>
				<td><%=item.getExpirationDate().toString()%></td>
				<td>$<%=String.format("%.2f", item.getPrice())%></td>
			</tr>
			<%
			}
	    }
	%>
</tbody>