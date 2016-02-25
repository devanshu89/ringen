/**
 * 
 */
package com.ringencorp.ezrtt.backend.model;

import com.google.appengine.api.datastore.GeoPt;

/**
 * @author Devanshu
 *
 */
public class EmpLoc {

	public EmpLoc() {

	}

	GeoPt ltln;

	String locdate;

	String empNum;

	public GeoPt getLtln() {
		return ltln;
	}

	public void setLtln(GeoPt ltln) {
		this.ltln = ltln;
	}

	public String getLocdate() {
		return locdate;
	}

	public void setLocdate(String locdate) {
		this.locdate = locdate;

	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public EmpLoc(GeoPt ltln, String locdate, String empNum) {
		super();
		this.ltln = ltln;
		this.locdate = locdate;
		this.empNum = empNum;
	}

}
