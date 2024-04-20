package org.cst8288.finalproject.users;

/**
 * Represents a retailer within the system. This class extends the {@link User} class,
 * incorporating additional attributes specific to retailers such as store information and location details.
 * 
 * This entity is crucial for managing information about retail-based users, allowing for operations
 * that include but are not limited to the handling of store names, addresses, and geographical data
 * which are pivotal for logistics and service delivery within the system.
 * 
 * Utilize this class to manage retail business profiles within the marketplace framework of the application.
 *
 * @author    Robin Phillis
 * @version   1.0
 * @since     2024-04-20
 * @see User  # Base class providing common user fields and functionality
 */
public class Retailer extends User {
    
    private int retailerID;
    private String storeName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;

    /**
     * Gets the unique identifier for the retailer.
     * @return the retailer's unique identifier
     */
    public int getRetailerID() 
    {
        return retailerID;
    }

    /**
     * Gets the name of the retailer's store.
     * @return the store name
     */
    public String getStoreName() 
    {
        return storeName;
    }

    /**
     * Gets the street address of the retailer's store.
     * @return the street address
     */
    public String getStreetAddress() 
    {
        return streetAddress;
    }

    /**
     * Gets the city where the retailer's store is located.
     * @return the city name
     */
    public String getCity() 
    {
        return city;
    }

    /**
     * Gets the province where the retailer's store is located.
     * @return the province name
     */
    public String getProvince() 
    {
        return province;
    }

    /**
     * Gets the postal code for the retailer's store location.
     * @return the postal code
     */
    public String getPostalCode() 
    {
        return postalCode;
    }

    /**
     * Sets the name of the retailer's store.
     * @param storeName the new store name
     */
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    /**
     * Sets the street address for the retailer's store.
     * @param streetAddress the new street address
     */
    public void setStreetAddress(String streetAddress) 
    {
        this.streetAddress = streetAddress;
    }

    /**
     * Sets the city where the retailer's store is located.
     * @param city the new city name
     */
    public void setCity(String city) 
    {
        this.city = city;
    }

    /**
     * Sets the province where the retailer's store is located.
     * @param province the new province name
     */
    public void setProvince(String province) 
    {
        this.province = province;
    }

    /**
     * Sets the postal code for the retailer's store location.
     * @param postalCode the new postal code
     */
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }
}
