package com.calendar.databaseapi.model;

import java.util.Set;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import javax.persistence.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Entity //save to a table
@Table(name = "events") // name of the table
public class Event {
	
    private int id;
    private String eventName;
    private Date startDate;
    private Date endDate;
    
    @ManyToMany(mappedBy = "assignedEvents")
    private Set<User> users;
    
    public Event() {
    }

    //TODO make start and end time strings
    public Event(String eventName, int year, int month, int day, int hour, int minute,
    							   int endYear,  int endMonth, int endDay, int endHour, int endMinute) { //end date
    	//TODO need to generate id
        this.eventName = eventName;
        this.startDate = new Date(year, month, day, hour, minute);
        this.endDate = new Date(endYear, endMonth, endDay, endHour, endMinute);
    }
    
    public ResponseEntity<Event> isValid(){
		if(startDate.isValid() && endDate.isValid()) {
    		return new ResponseEntity<Event>(HttpStatus.OK);
    	}
		else return new ResponseEntity<Event>(HttpStatus.NOT_ACCEPTABLE);
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