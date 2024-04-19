package org.cst8288.finalproject.users;

public class CharitableOrganization extends User {

    private int organizationID;
    private String organizationName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    // Getters
    public int getOrganizationID() 
    {
        return organizationID;
    }

    public String getOrganizationName() 
    {
        return organizationName;
    }
    
	public String getStreetAddress() 
	{
		return streetAddress;
	}

    public String getCity() 
    {
        return city;
    }

    public String getProvince() 
    {
        return province;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }

    public String getCountry() 
    {
        return country;
    }

    // Setters
    public void setOrganizationName(String organizationName) 
    {
        this.organizationName = organizationName;
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

    public void setCountry(String country) 
    {
        this.country = country;
    }
}
