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

@Entity //save to a table
@Table(name = "events") // name of the table
public class Event {
	
    private int id;
    private String eventName;
    private Date date;
    
    @ManyToMany(mappedBy = "assignedEvents")
    private Set<User> users;
    
    public Event() {
    }

    public Event(int id, String eventName, int year, int month, int day, int hour, int minute) {
        this.id = id;
        this.eventName = eventName;
        this.date = new Date(year, month, day, hour, minute);
        
    }
    public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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