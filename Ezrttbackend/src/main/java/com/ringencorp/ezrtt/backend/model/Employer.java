package com.ringencorp.ezrtt.backend.model;

import com.google.appengine.api.datastore.GeoPt;

/**
 * The object model for the data we are sending through endpoints
 */
public class Employer {

	private String name;

	private String address;

	private GeoPt latlng;

	private String country;

	private long headcount;

	private String email;

	private boolean active;

	private int weekStartDay;

	private int weekEndDay;

	public int getWeekStartDay() {
		return weekStartDay;
	}

	public void setWeekStartDay(int weekStartDay) {
		this.weekStartDay = weekStartDay;
	}

	public int getWeekEndDay() {
		return weekEndDay;
	}

	public void setWeekEndDay(int weekEndDay) {
		this.weekEndDay = weekEndDay;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GeoPt getLatlng() {
		return latlng;
	}

	public void setLatlng(GeoPt latlng) {
		this.latlng = latlng;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getHeadcount() {
		return headcount;
	}

	public void setHeadcount(long headcount) {
		this.headcount = headcount;
	}

	public Employer() {

	}

	public Employer(String name, String address, GeoPt latlng, String country, long headcount, String email,
			boolean active, int wkDayStart, int wkDayEnd) {
		super();
		this.name = name;
		this.address = address;
		this.latlng = latlng;
		this.country = country;
		this.headcount = headcount;
		this.email = email;
		this.active = active;
		this.weekEndDay = wkDayEnd;
		this.weekStartDay = wkDayStart;

	}

	public Employer(String email, String name) {
		this.email = email;
		this.name = name;
	}

}