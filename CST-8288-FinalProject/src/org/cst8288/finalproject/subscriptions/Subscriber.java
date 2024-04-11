package org.cst8288.finalproject.subscriptions;

import org.cst8288.finalproject.enums.ContactMethod;

public class Subscriber {
	private int id;
	private ContactMethod subMethod;
	private String info;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ContactMethod getMethod() {
		return subMethod;
	}
	public void setMethod(ContactMethod method) {
		this.subMethod = method;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
