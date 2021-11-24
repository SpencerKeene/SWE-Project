package com.calendar.databaseapi.model;

import java.util.HashSet;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import javax.persistence.*;

@Entity //save to a table
@Table(name = "events") // name of the table
public class Event {
	
    private int id;
    private String eventName;
    private Date startDate;
    private Date endDate;
    
    @ManyToMany(mappedBy = "assignedEvents")
    private HashSet<User> users;
    
    public Event() {
    }

    public Event(int id, String eventName, int day, int month, int year, int startHour, int startMinute, int endHour, int endMinute) {
    	this.id = id;
        this.eventName = eventName;
        this.startDate = new Date(year, month, day, startHour, startMinute);
        this.endDate = new Date(year, month, day, endHour, endMinute);
    }
    
    public boolean isValid() {
		return startDate.isValid() && endDate.isValid();
	}
    
    public boolean isConflict(Event event) {
    	Date startDate2 = event.getStartDate();
    	Date endDate2 = event.getEndDate();
    	
    	if(endDate.isBefore(startDate2)) return false;
    	else if(startDate.isAfter(endDate2)) return false;
    	else return true;
    }
    
    //getters and setters
    public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
}