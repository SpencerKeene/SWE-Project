package com.calendar.databaseapi.model;

public class Date {

	int year;
	int month;
	int day;
	int hour;
	int minute;
	
	public Date() {
	}
	
	public Date(String dateString) {
		String[] dateComponents = dateString.split(" ");
		this.year = Integer.parseInt(dateComponents[0]);
		this.month = Integer.parseInt(dateComponents[1]);
		this.day = Integer.parseInt(dateComponents[2]);
		String[] timeComponents = dateComponents[3].split(":");
		this.hour = Integer.parseInt(timeComponents[0]);
		this.minute = Integer.parseInt(timeComponents[1]);
	}
	
	public Date(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public boolean isValid() {
		if(month > 12 || month <= 0) return false;
		else if(day > 31 || day <= 0) return false;
		else if(hour >= 24 || hour < 0) return false;
		else if(minute >= 60 || minute < 0) return false;
		return true;
	}
	
	public boolean isBefore(Date date) {
		if(year < date.year) return true;
		else if(year == date.year && month < date.month) return true;
		else if(year == date.year && month == date.month && day < date.month) return true; 
		else if(year == date.year && month == date.month && day == date.day && hour < date.hour) return true;
		else if(year == date.year && month == date.month && day == date.day && hour == date.hour && minute < date.minute) return true;
		else return false;
	}
	
	public boolean isAfter(Date date) {
		if(year > date.year) return true;
		else if(year == date.year && month > date.month) return true;
		else if(year == date.year && month == date.month && day > date.month) return true; 
		else if(year == date.year && month == date.month && day == date.day && hour > date.hour) return true;
		else if(year == date.year && month == date.month && day == date.day && hour == date.hour && minute > date.minute) return true;
		else return false;
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
	
	public int compareTo(Date x) {
		if(x.year == year && x.month == month &&
		   x.day == day && x.hour == hour && x.minute == minute) {
			return 0;
		}
		
		else if(year >= x.year && month >= x.month && 
				day >= x.day && hour >= x.hour && minute >= minute) { // >= allows the two times to have the same parameter
			return 1;												  // since it checks for == first this cannot be equal
		}
		
		else return -1;
	}
	
}
