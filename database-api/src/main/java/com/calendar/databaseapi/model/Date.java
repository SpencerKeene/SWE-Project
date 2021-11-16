package com.calendar.databaseapi.model;

public class Date {

	int year;
	int month;
	int day;
	int hour;
	int minute;
	
	public Date() {
	}
	
	public Date(int year, int month, int day, int hour, int minute) {
		
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return day;
	}

	public void setDate(int date) {
		this.day = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
}
