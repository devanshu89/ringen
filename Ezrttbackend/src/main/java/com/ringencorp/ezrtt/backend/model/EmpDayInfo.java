package com.ringencorp.ezrtt.backend.model;

import java.util.ArrayList;

public class EmpDayInfo {
	private int dayOfWeek;

	ArrayList<EmpHourInfo> hourInfo;

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public ArrayList<EmpHourInfo> getHourInfo() {
		return hourInfo;
	}

	public void setHourInfo(ArrayList<EmpHourInfo> hourInfo) {
		this.hourInfo = hourInfo;
	}

	public EmpDayInfo(int dayOfWeek, ArrayList<EmpHourInfo> hourInfo) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.hourInfo = hourInfo;
	}

}
