package com.ringencorp.ezrtt.backend.model;

import com.google.appengine.api.datastore.GeoPt;

/**
 * @author Devanshu
 *
 */
public class Employee {

	private String employername;

	private String firstName;

	private String lastName;

	private String email;

	private String mobile;

	private String inTime;

	private String outTime;

	private int weekDayStart;

	private int weekDayEnd;

	GeoPt offAddress;

	public int getWeekDayStart() {
		return weekDayStart;
	}

	public void setWeekDayStart(int weekDayStart) {
		this.weekDayStart = weekDayStart;
	}

	public int getWeekDayEnd() {
		return weekDayEnd;
	}

	public void setWeekDayEnd(int weekDayEnd) {
		this.weekDayEnd = weekDayEnd;
	}

	public String getEmployername() {
		return employername;
	}

	public void setEmployername(String employername) {
		this.employername = employername;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	private boolean status;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String lastName, String email, String mobile, boolean status, String emper,
			String intime, String outtime, int wkStart, int wkEnd) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
		this.employername = emper;
		this.weekDayStart = wkStart;
		this.weekDayEnd = wkEnd;

	}

	public Employee(String emper, GeoPt latlng, String inTime, String outTime, int wkStart, int wkEnd) {
		this.employername = emper;
		this.weekDayStart = wkStart;
		this.weekDayEnd = wkEnd;
		this.offAddress = latlng;
		this.inTime = inTime.toString();
		this.outTime = outTime.toString();
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public GeoPt getOffAddress() {
		return offAddress;
	}

	public void setOffAddress(GeoPt offAddress) {
		this.offAddress = offAddress;
	}

}
