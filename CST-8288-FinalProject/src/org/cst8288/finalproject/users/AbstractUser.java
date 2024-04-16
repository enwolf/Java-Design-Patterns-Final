package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

public abstract class AbstractUser {

	protected int userID;
	protected String userFirstName;
	protected String userLastName;
	protected String emailAddress;
	protected UserType userType;

	public abstract int getUserId();

	public abstract String getUserFirstName();

	public abstract String getUserLastName();

	public abstract String getEmailAddress();

	public abstract UserType getUserType();

	public abstract void setUserId(int userId);

	public abstract void setUserFirstName(String userFirstName);

	public abstract void setUserLastName(String userLastName);

	public abstract void setEmailAddress(String emailAddress);

	public abstract void setUserType(UserType userType);

}
