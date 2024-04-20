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

public class AddItemToInventoryServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final LMSLogger logger = LMSLogger.getInstance();
    private static final String CLASS_NAME = "AddItemToInventoryServlet";
    //private InventoryManager inventoryManager;
    private InventoryManagerDAO inventoryDAO;

    @Override
    public void init() throws ServletException 
    {
        logger.info(CLASS_NAME + ": Initializing AddItemToInventoryServlet...");
        //this.inventoryManager = new InventoryManager(new InventoryManagerDAO(), new FoodItemValidator(new InventoryManagerDAO()));
        this.inventoryDAO = new InventoryManagerDAO();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user != null && user.getUserType() == UserType.RETAILER) 
        {
            logger.info(CLASS_NAME + ": Forwarding to addItemToInventory.jsp for user: " + user.getUserId());
            request.getRequestDispatcher("/jsp/addItemToInventory.jsp").forward(request, response);
        }
        else 
        {
            logger.warn(CLASS_NAME + ": Unauthorized access attempt in doGet()");
            session.setAttribute("error", "Unauthorized access attempt.");
            response.sendRedirect("login.jsp"); 
        }
    }

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

    @Override
    public void destroy() 
    {
        logger.info(CLASS_NAME + ": Destroying AddItemToInventoryServlet...");
        inventoryDAO = null;
    }
}
