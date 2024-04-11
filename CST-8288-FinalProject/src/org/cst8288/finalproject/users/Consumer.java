package org.cst8288.finalproject.users;

public class Consumer {
    private int consumerID;
    private int userID;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private double accountBalanace;

    public double getAccountBalanace() {
		return accountBalanace;
	}

	public void setAccountBalanace(double accountBalanace) {
		this.accountBalanace = accountBalanace;
	}

	// Getters and Setters
    public int getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(int consumerID) {
        this.consumerID = consumerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
