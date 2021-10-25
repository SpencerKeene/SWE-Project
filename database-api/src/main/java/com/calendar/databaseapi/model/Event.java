package com.calendar.databaseapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //save to a table
@Table(name = "events") // name of the table
public class Event {
    private int id;
    private String eventName;
    private Date date;
    
    public Event() {
    }

    public Event(int id, String eventName, int year, int month, int date, int hour, int minute) {
        this.id = id;
        this.eventName = eventName;
        this.date = new Date(year, month, date, hour, minute);
        
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