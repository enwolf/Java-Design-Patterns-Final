<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Item to Inventory</title>
    <link rel="stylesheet" href="../css/addItemToInventory.css">
</head>
<body>
    <div class="container">
        <h2>Add Item to Inventory</h2>
        <form action="${pageContext.request.contextPath}/addInventoryItem" method="POST">
            <input type="text" name="itemName" placeholder="Item Name" required>
            <input type="number" name="quantity" placeholder="Quantity" required min="1">
            <input type="date" name="expirationDate" placeholder="Expiration Date" required>
            <input type="text" name="price" placeholder="Price" required pattern="^\d+(\.\d{1,2})?$" title="Price format: 123 or 123.45">
            <input type="text" name="discountRate" placeholder="Discount Rate (%)" pattern="^\d+(\.\d{1,2})?$" title="Rate format: 12.34"><!-- Input for Discount Amount -->
            <input type="text" name="discountAmount" placeholder="Discount Amount" pattern="^\d+(\.\d{1,2})?$" title="Amount format: 123 or 123.45" >
            
            
            <button type="submit" class="button">Add Item</button>
        </form>
    </div>
</body>
</html>
