package com.ringencorp.ezrtt.backend.model.entities;

import com.ringencorp.ezrtt.backend.constants.EngineConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

// DECLACRING EMPEE OBJECT AS PERSISTENT CAPABLE
@PersistenceCapable
public class ModelEmpee extends ModelBase {

	@Persistent
	private String employername;

	@Persistent
	private String firstName;

	@Persistent
	private String lastName;

	@Persistent
	private String email;

	@Persistent
	private String mobile;

	@Persistent
	private boolean active;

	@Persistent
	private String inTime;

	@Persistent
	private String outTime;

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

	public ModelEmpee() {

		super(new SimpleDateFormat(EngineConstants.DATE_PATTERN).format(Calendar.getInstance().getTime()));

	}

	/**
	 * @return the employername
	 */
	public String getEmployername() {
		return employername;
	}

	/**
	 * @param employername
	 *            the employername to set
	 */
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

	public ModelEmpee(String id, String employername, String firstName, String lastName, String email, String mobile,
			String intime, String outTime) {
		super(id);
		this.employername = employername;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.inTime = intime;
		this.outTime = outTime;

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

}
