package com.ringencorp.ezrtt.backend.model;

import java.util.ArrayList;

public class EmpWeekInfo {

	private int dayOfwkStart, dayOfwkEnd;

	ArrayList<EmpDayInfo> dayInfo;

	public EmpWeekInfo(int weekOfYear, int inHour, int outHour, ArrayList<EmpDayInfo> dayInfo) {
		super();
		this.dayInfo = dayInfo;
	}

	public ArrayList<EmpDayInfo> getDayInfo() {
		return dayInfo;
	}

	public void setDayInfo(ArrayList<EmpDayInfo> dayInfo) {
		this.dayInfo = dayInfo;
	}

	public int getDayOfwkStart() {
		return dayOfwkStart;
	}

	public void setDayOfwkStart(int dayOfwkStart) {
		this.dayOfwkStart = dayOfwkStart;
	}

	public int getDayOfwkEnd() {
		return dayOfwkEnd;
	}

	public void setDayOfwkEnd(int dayOfwkEnd) {
		this.dayOfwkEnd = dayOfwkEnd;
	}

	public EmpWeekInfo() {

	}

}
