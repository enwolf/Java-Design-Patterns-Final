package org.cst8288.finalproject.users;

/**
 * Represents a consumer user within the system. This class extends the {@link User} class,
 * adding specific properties that are unique to consumers such as contact information and address details.
 * 
 * This entity can be used to manage the personal and contact details of consumers which include their
 * phone numbers and residential addresses, essential for communications and service delivery within the system.
 * 
 * Use this class to handle and store personal information about consumers that can assist in various business processes.
 *
 * @author    Robin Phillis
 * @version   1.0
 * @since     2024-04-20
 * @see User  
 */
public class Consumer extends User {

    private int consumerID;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private double accountBalance;        
    
    /**
     * Gets the unique identifier for the consumer.
     * @return the consumer's unique identifier
     */
    public int getConsumerID() 
    {
        return consumerID;
    }
    
    /**
     * Gets the phone number of the consumer.
     * @return the consumer's phone number
     */
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    /**
     * Gets the street address of the consumer.
     * @return the street address
     */
    public String getStreetAddress() 
    {
        return streetAddress;
    }
    
    /**
     * Gets the city where the consumer resides.
     * @return the city name
     */
    public String getCity() 
    {
        return city;
    }
    
    /**
     * Gets the province where the consumer resides.
     * @return the province name
     */
    public String getProvince() 
    {
        return province;
    }
    
    /**
     * Gets the postal code for the consumer's location.
     * @return the postal code
     */
    public String getPostalCode() 
    {
        return postalCode;
    }
 
    /**
     * Gets the account balance currently associated with the consumer.
     * @return the account balance
     */
	public double getAccountBalance() 
	{
		return accountBalance;
	}

    /**
     * Sets the phone number for the consumer.
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the street address for the consumer.
     * @param streetAddress the new street address
     */
    public void setStreetAddress(String streetAddress) 
    {
        this.streetAddress = streetAddress;
    }

    /**
     * Sets the city where the consumer resides.
     * @param city the new city name
     */
    public void setCity(String city) 
    {
        this.city = city;
    }

    /**
     * Sets the province where the consumer resides.
     * @param province the new province name
     */
    public void setProvince(String province) 
    {
        this.province = province;
    }

    /**
     * Sets the postal code for the consumer's location.
     * @param postalCode the new postal code
     */
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    /**
     * Sets the account balance associated with the consumer.
     * @param accountBalance the new account balance
     */
	public void setAccountBalance(double accountBalance) 
	{
		this.accountBalance = accountBalance;
	}
}
