package org.cst8288.finalproject.users;

/**
 * Represents a charitable organization within the system. This class extends the {@link User} class,
 * adding specific properties that are unique to charitable organizations such as organization name and address details.
 * 
 * This entity can be used to manage the information related to charitable organizations which can include their
 * location details and other organization-specific data which are important for the system's operations concerning
 * these types of users.
 *  
 * Use this class to handle and store information related to any charitable organization within your application context.
 *
 * @author 	author Robin Phillis
 * @version 1.0
 * @since 	2024-04-20
 * @see User 
 */
public class CharitableOrganization extends User {

    private int organizationID;
    private String organizationName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    /**
     * Gets the unique identifier for the charitable organization.
     * @return the organization's unique identifier
     */
    public int getOrganizationID() 
    {
        return organizationID;
    }

    /**
     * Gets the name of the charitable organization.
     * @return the organization's name
     */
    public String getOrganizationName() 
    {
        return organizationName;
    }

    /**
     * Gets the street address of the charitable organization.
     * @return the street address
     */
    public String getStreetAddress() 
    {
        return streetAddress;
    }

    /**
     * Gets the city where the charitable organization is located.
     * @return the city name
     */
    public String getCity() 
    {
        return city;
    }

    /**
     * Gets the province where the charitable organization is located.
     * @return the province name
     */
    public String getProvince() 
    {
        return province;
    }

    /**
     * Gets the postal code for the location of the charitable organization.
     * @return the postal code
     */
    public String getPostalCode() 
    {
        return postalCode;
    }

    /**
     * Gets the country where the charitable organization is located.
     * @return the country name
     */
    public String getCountry() 
    {
        return country;
    }

    /**
     * Sets the name of the charitable organization.
     * @param organizationName the new name of the organization
     */
    public void setOrganizationName(String organizationName) 
    {
        this.organizationName = organizationName;
    }

    /**
     * Sets the street address of the charitable organization.
     * @param streetAddress the new street address
     */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    /**
     * Sets the city where the charitable organization is located.
     * @param city the new city name
     */
    public void setCity(String city) 
    {
        this.city = city;
    }

    /**
     * Sets the province where the charitable organization is located.
     * @param province the new province name
     */
    public void setProvince(String province) 
    {
        this.province = province;
    }

    /**
     * Sets the postal code for the location of the charitable organization.
     * @param postalCode the new postal code
     */
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    /**
     * Sets the country where the charitable organization is located.
     * @param country the new country name
     */
    public void setCountry(String country) 
    {
        this.country = country;
    }
}

