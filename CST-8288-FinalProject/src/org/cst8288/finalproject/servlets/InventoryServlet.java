package org.cst8288.finalproject.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cst8288.finalproject.dao.InventoryManagerDAO;
import org.cst8288.finalproject.dto.FoodItem;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.manager.InventoryManager;
import org.cst8288.finalproject.validator.FoodItemValidator;

/**
 * Servlet implementation for handling inventory display operations.
 * This servlet is responsible for retrieving all items from the inventory, specifically filtering and
 * casting them to {@link FoodItem} objects, and then forwarding these items to a JSP page for display.
 * 
 * The servlet makes use of {@link InventoryManager} to interact with the inventory data and ensures
 * that any interaction with the inventory is logged for auditing and debugging purposes.
 * 
 * Detailed logging is performed to ensure that all steps of the process are recorded, making debugging
 * and verification of processes easier and more intuitive.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see InventoryManager
 * @see FoodItem
 * @see LMSLogger
 */
public class InventoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final LMSLogger logger = LMSLogger.getInstance();
    private static final String CLASS_NAME = "InventoryServlet";

    /**
     * Handles the HTTP GET requests by fetching inventory items from the database and preparing them for display.
     * This method queries the inventory, filters for {@link FoodItem} instances, and forwards them to the 'inventory.jsp' page.
     * It uses {@link InventoryManager} to retrieve and manage inventory data effectively.
     *
     * All operations are logged using {@link LMSLogger} to provide traceability and debug information.
     * 
     * @param request  Servlet request which may carry parameters for filtering or other data retrieval options.
     * @param response Servlet response to forward the results to the appropriate JSP page for user display.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs due to interaction with the servlet or while forwarding to the JSP.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        logger.info(CLASS_NAME + ": Servlet accessed.");
        logger.debug(CLASS_NAME + ": Entering doGet()");

        // Perform the inventory retrieval only if not already done for this session to avoid redundant database calls
        if (request.getAttribute("inventoryItems") == null) 
        {
            InventoryManagerDAO inventoryDAO = new InventoryManagerDAO();
            FoodItemValidator foodItemValidator = new FoodItemValidator(inventoryDAO);
            InventoryManager inventoryManager = new InventoryManager(inventoryDAO, foodItemValidator);

            logger.info(CLASS_NAME + ": Fetching all inventory items...");
            List<Item> currentInventoryList = inventoryManager.getAllInventoryItems();
            logger.debug(CLASS_NAME + ": Number of items fetched: " + currentInventoryList.size());

            // Filter and collect only FoodItem instances from the inventory list
            List<FoodItem> foodItems = new ArrayList<>();
            for (Item item : currentInventoryList) 
            {
                if (item instanceof FoodItem) 
                {
                    foodItems.add((FoodItem) item);
                }
            }

            logger.debug(CLASS_NAME + ": Number of FoodItems after casting: " + foodItems.size());
            request.setAttribute("inventoryItems", foodItems);
        }

        logger.info(CLASS_NAME + ": Dispatching to JSP page...");
        request.getRequestDispatcher("/jsp/inventory.jsp").forward(request, response);
        logger.debug(CLASS_NAME + ": Exiting doGet()");
    }
}
