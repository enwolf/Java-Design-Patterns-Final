package org.cst8288.finalproject.users;

public class Consumer extends User {

    private int consumerID;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private double accountBalance;        
    
    
    public int getConsumerID() 
    {
        return consumerID;
    }
    
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    public String getStreetAddress() 
    {
        return streetAddress;
    }
    
    public String getProvince() 
    {
        return province;
    }
    
    public String getCity() 
    {
        return city;
    }
    
    public String getPostalCode() 
    {
        return postalCode;
    }
 
	public double getAccountBalance() 
	{
		return accountBalance;
	}

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) 
    {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public void setProvince(String province) 
    {
        this.province = province;
    }

    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
}
