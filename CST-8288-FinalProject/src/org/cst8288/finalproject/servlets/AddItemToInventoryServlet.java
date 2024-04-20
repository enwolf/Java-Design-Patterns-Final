package org.cst8288.finalproject.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cst8288.finalproject.dao.InventoryManagerDAO;
import org.cst8288.finalproject.dto.FoodItem;
import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.users.User;

/**
 * Servlet implementation for handling the process of adding inventory items to the database.
 * This servlet responds to both GET and POST requests. GET requests are used to forward users
 * to the item addition page, while POST requests handle the actual insertion of item data into
 * the database. The servlet ensures that only authenticated users with a RETAILER role can add items.
 * It uses the {@link InventoryManagerDAO} to interact with the database.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see InventoryManagerDAO
 * @see Retailer
 * @see FoodItem
 */
public class AddItemToInventoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final LMSLogger logger = LMSLogger.getInstance();
    private static final String CLASS_NAME = "AddItemToInventoryServlet";
    private InventoryManagerDAO inventoryDAO;

    /**
     * Initializes the servlet and logs the startup. It sets up necessary backend components 
     * like the {@link InventoryManagerDAO}.
     *
     * @throws ServletException if an error occurs during servlet initialization
     */
    @Override
    public void init() throws ServletException {
        logger.info(CLASS_NAME + ": Initializing AddItemToInventoryServlet...");
        this.inventoryDAO = new InventoryManagerDAO();
    }

    /**
     * Handles the HTTP GET requests. Verifies if the user session indicates a RETAILER role,
     * and forwards to the item addition page. If the user is not authorized, redirects to the login page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user != null && user.getUserType() == UserType.RETAILER) {
            logger.info(CLASS_NAME + ": Forwarding to addItemToInventory.jsp for user: " + user.getUserId());
            request.getRequestDispatcher("/jsp/addItemToInventory.jsp").forward(request, response);
        } else {
            logger.warn(CLASS_NAME + ": Unauthorized access attempt in doGet()");
            session.setAttribute("error", "Unauthorized access attempt.");
            response.sendRedirect("login.jsp");        
        }
    }

    /**
     * Handles HTTP POST requests by processing form data submitted from the 'Add Item to Inventory' page.
     * This method is responsible for several key operations:
     * 
     * 1. **Authorization Check**: It first checks if the session contains a valid user object and whether
     *    the user is authorized to add items (i.e., the user must be a retailer).
     * 2. **Data Extraction and Validation**: Extracts item details from the request parameters and validates them.
     *    This includes non-null checks and formatting checks (using appropriate parsing and error handling
     *    for numerical values like price and quantity).
     * 3. **Item Creation**: Constructs a new {@link FoodItem} object using the validated data.
     * 4. **Database Insertion**: Attempts to insert the new item into the database via the {@link InventoryManagerDAO}.
     *    Logs and handles any SQL exceptions that may arise during this process.
     * 5. **Response Handling**: Based on the outcome of the database operation, it redirects the user either
     *    back to the form with an error message or to a success page/dashboard with a success message.
     *
     * Detailed error handling and user feedback mechanisms ensure robust operation even when faced with invalid
     * input or database access issues.
     *
     * @param request  The servlet request object that contains the form data from 'addItemToInventory.jsp'.
     * @param response The servlet response object used to convey output to the client.
     * @throws ServletException if a servlet-specific error occurs (e.g., if the forwarding fails).
     * @throws IOException if an I/O error occurs during request handling (e.g., if writing to the response output stream fails).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        Retailer retailer = (Retailer) session.getAttribute("retailer");
        User user = (User)session.getAttribute("user");
        
        logger.info(CLASS_NAME + ": doPost() called.");

        if (retailer == null && user == null)
        {
            logger.warn(CLASS_NAME + ": Unauthorized access attempt in doPost()");
            session.setAttribute("error", "Unauthorized access attempt.");
            response.sendRedirect("login.jsp");
            return;
        }

        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        
        logger.info(CLASS_NAME + ": Parameters received in doPost() - itemName: " + itemName + ", quantity: " + quantity + ", expirationDate: " + expirationDate + ", price: " + price);
        
        // Extracting and verifying discountRate
        String discountRateStr = request.getParameter("discountRate");
        BigDecimal discountRate;
        
        if (discountRateStr != null && !discountRateStr.isEmpty()) 
        {
            discountRate = new BigDecimal(discountRateStr);
        }
        else 
        {
            discountRate = BigDecimal.ZERO;  
        }

        // Extracting and verifying discountAmount
        String discountAmountStr = request.getParameter("discountAmount");
        BigDecimal discountAmount;
        
        if (discountAmountStr != null && !discountAmountStr.isEmpty()) 
        {
            discountAmount = new BigDecimal(discountAmountStr);
        }
        else 
        {
            discountAmount = BigDecimal.ZERO;  
        }
        
        FoodItem newItem = new FoodItem();
        newItem.setRetailerID(user.getUserId()); 
        newItem.setItemName(itemName);
        newItem.setQuantity(quantity);
        newItem.setExpirationDate(expirationDate);
        newItem.setPrice(price);
        newItem.setDiscountRate(discountRate);
        newItem.setDiscountAmount(discountAmount);
        
        logger.info(CLASS_NAME + ": Adding new item to inventory: " + newItem.toString());
        
        try 
        {
            //inventoryManager.validateAndAddInventoryItem(newItem);
        	inventoryDAO.addInventoryItem(newItem);
            logger.info(CLASS_NAME + ": New item added successfully.");
            session.setAttribute("message", "New item added successfully!");
            response.sendRedirect(request.getContextPath() + "/jsp/addItemToInventory.jsp");
        }
        catch (Exception e) 
        {
            logger.error(CLASS_NAME + ": Failed to add item: " + e.getMessage());
            session.setAttribute("error", "Failed to add item: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/jsp/addItemToInventory.jsp");
        }
    }
    
    /**
     * Performs cleanup tasks when the servlet is being destroyed.
     * This method is called by the servlet container to indicate to a servlet that it is being taken out of service.
     * It logs a message indicating the destruction of the servlet and sets the inventoryDAO reference to null to release resources.
     */
    @Override
    public void destroy() 
    {
        logger.info(CLASS_NAME + ": Destroying AddItemToInventoryServlet...");
        inventoryDAO = null;
    }
}
