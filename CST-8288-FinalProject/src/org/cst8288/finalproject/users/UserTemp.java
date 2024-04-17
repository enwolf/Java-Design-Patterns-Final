package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

public abstract class UserTemp {

	private UserType type;

	public UserType getType() 
	{
		return type;
	}

	public void setType(UserType type) 
	{
		this.type = type;
	}
}
