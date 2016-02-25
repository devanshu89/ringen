/**
 * 
 */
package com.ringencorp.ezrtt.backend.model.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.google.appengine.api.datastore.GeoPt;
import com.ringencorp.ezrtt.backend.constants.EngineConstants;

/**
 * @author Devanshu
 * 
 */
// DECLACRING EMPER OBJECT AS PERSISTENT CAPABLE
@PersistenceCapable
public class ModelEmper extends ModelBase {

	@Persistent
	private String name;

	@Persistent
	private String address;

	@Persistent
	private GeoPt latlng;

	@Persistent
	private String country;

	@Persistent
	private long headcount;

	@Persistent
	private boolean active;

	@Persistent
	private int weekStartDay;

	@Persistent
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the latlng
	 */
	public GeoPt getLatlng() {
		return latlng;
	}

	/**
	 * @param latlng
	 *            the latlng to set
	 */
	public void setLatlng(GeoPt latlng) {
		this.latlng = latlng;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the headcount
	 */
	public long getHeadcount() {
		return headcount;
	}

	/**
	 * @param headcount
	 *            the headcount to set
	 */
	public void setHeadcount(long headcount) {
		this.headcount = headcount;
	}

	public ModelEmper() {
		super(new SimpleDateFormat(EngineConstants.DATE_PATTERN).format(Calendar.getInstance().getTime()));
	}

	public ModelEmper(String id, String name, String address, GeoPt latlng, String country, long headcount,
			int wkStartday, int wkEndDay) {
		super(id);
		this.name = name;
		this.address = address;
		this.latlng = latlng;
		this.country = country;
		this.headcount = headcount;
		this.weekStartDay = wkStartday;
		this.weekEndDay = wkEndDay;
	}

}
