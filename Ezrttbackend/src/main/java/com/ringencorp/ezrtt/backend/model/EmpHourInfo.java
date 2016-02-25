package com.ringencorp.ezrtt.backend.model;

import java.util.ArrayList;

public class EmpHourInfo {

	private int hourOfDay;
	
	private int samples;

	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	private ArrayList<Boolean> presences;

	public int getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public ArrayList<Boolean> getPresences() {
		return presences;
	}

	public void setPresences(ArrayList<Boolean> presences) {
		this.presences = presences;
	}

	public EmpHourInfo() {

	}

	public EmpHourInfo(int hourOfDay, ArrayList<Boolean> presences, int samples) {
		super();
		this.hourOfDay = hourOfDay;
		this.presences = presences;
		this.samples = samples;
	}

}
