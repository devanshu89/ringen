/**
 * 
 */
package com.ringencorp.ezrtt.backend.model;

/**
 * @author Devanshu
 *
 */
public class Status {
	private boolean updated;

	private boolean emper_present;
	
	private String data;

	/**
	 * @return the isEmper
	 */
	public boolean isEmper() {
		return emper_present;
	}

	/**
	 * @param isEmper
	 *            the isEmper to set
	 */
	public void setEmper(boolean isEmper) {
		this.emper_present = isEmper;
	}

	/**
	 * @return the updated
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public Status(boolean updated) {
		this.updated = updated;
	}

	public Status() {
		updated = false;
		emper_present = false;
	}

	public boolean isEmper_present() {
		return emper_present;
	}

	public void setEmper_present(boolean emper_present) {
		this.emper_present = emper_present;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
