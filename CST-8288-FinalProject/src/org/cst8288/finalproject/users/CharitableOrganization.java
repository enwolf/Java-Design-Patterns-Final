package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserInterface;

public class CharitableOrganization implements UserInterface{

	 private int organizationID;
	    private int userID;

	    private String organizationName;

	    private String city;

	    private String province;

	    private String postalCode;

	    private String country;
	    
	    
	public int getOrganizationID() {
			return organizationID;
		}

		public void setOrganizationID(int organizationID) {
			this.organizationID = organizationID;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public String getOrganizationName() {
			return organizationName;
		}

		public void setOrganizationName(String organizationName) {
			this.organizationName = organizationName;
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

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return 0;
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
	public UserType getUserType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserId(int userId) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void setUserType(UserType userType) {
		// TODO Auto-generated method stub
		
	}
	
	

}
