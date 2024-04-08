package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserInterface;

public class Consumer implements UserInterface{

 	private int consumerID;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
	
    
	public int getConsumerID() {
		return consumerID;
	}

	public void setConsumerID(int consumerID) {
		this.consumerID = consumerID;
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

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserType getUserType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserId(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserType(UserType userType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserFirstName(String userFirstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserLastName(String userLastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		
	}
	
	

}
