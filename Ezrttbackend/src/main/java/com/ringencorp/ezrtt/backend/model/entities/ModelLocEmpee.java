package com.ringencorp.ezrtt.backend.model.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.google.appengine.api.datastore.GeoPt;
import com.ringencorp.ezrtt.backend.utils.EngineConstants;

@PersistenceCapable
public class ModelLocEmpee extends ModelBase {

	@Persistent
	GeoPt ltln;

	@Persistent
	Date locdatetime;

	@Persistent
	String employeename;

	@Persistent
	boolean isInOffice;

	public GeoPt getLtln() {
		return ltln;
	}

	public void setLtln(GeoPt ltln) {
		this.ltln = ltln;
	}

	public Date getLocdate() {
		return locdatetime;
	}

	public void setLocdate(Date locdate) {
		this.locdatetime = locdate;
	}

	public String getEmpNum() {
		return employeename;
	}

	public void setEmpNum(String empNum) {
		this.employeename = empNum;
	}

	public Date getLocdatetime() {
		return locdatetime;
	}

	public void setLocdatetime(Date locdatetime) {
		this.locdatetime = locdatetime;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public boolean isInOffice() {
		return isInOffice;
	}

	public void setInOffice(boolean isInOffice) {
		this.isInOffice = isInOffice;
	}

	public ModelLocEmpee(GeoPt ltln, Date locdate, String empNum, boolean inOffice) {
		super(new SimpleDateFormat(EngineConstants.DATE_PATTERN).format(Calendar.getInstance().getTime()));
		this.ltln = ltln;
		this.locdatetime = locdate;
		this.employeename = empNum;
		this.isInOffice = inOffice;
	}

}
