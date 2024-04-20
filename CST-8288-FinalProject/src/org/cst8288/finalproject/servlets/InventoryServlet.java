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

public class InventoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final LMSLogger logger = LMSLogger.getInstance();
    private static final String CLASS_NAME = "InventoryServlet";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(CLASS_NAME + ": Servlet accessed.");
        logger.debug(CLASS_NAME + ": Entering doGet()");

        if (request.getAttribute("inventoryItems") == null) {
            InventoryManagerDAO inventoryDAO = new InventoryManagerDAO();
            FoodItemValidator foodItemValidator = new FoodItemValidator(inventoryDAO);
            InventoryManager inventoryManager = new InventoryManager(inventoryDAO, foodItemValidator);

            logger.info(CLASS_NAME + ": Fetching all inventory items...");
            List<Item> currentInventoryList = inventoryManager.getAllInventoryItems();
            logger.debug(CLASS_NAME + ": Number of items fetched: " + currentInventoryList.size());

            List<FoodItem> foodItems = new ArrayList<>();
            for (Item item : currentInventoryList) {
                if (item instanceof FoodItem) {
                    foodItems.add((FoodItem) item);
                }
            }

            logger.debug(CLASS_NAME + ": Number of FoodItems after casting: " + foodItems.size());
            request.setAttribute("inventoryItems", foodItems);
        }

        logger.info(CLASS_NAME + ": Dispatching to JSP page...");
        //request.getRequestDispatcher(request.getContextPath() + "/inventory").forward(request, response);
        request.getRequestDispatcher("/jsp/inventory.jsp").forward(request, response);
        logger.debug(CLASS_NAME + ": Exiting doGet()");
    }
}
