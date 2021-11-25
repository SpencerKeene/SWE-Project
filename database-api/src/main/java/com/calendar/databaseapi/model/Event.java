package com.calendar.databaseapi.model;

import java.util.HashSet;
import java.util.Set;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //save to a table
@Table(name = "events") // name of the table
public class Event {
	
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private Set<User> users = new HashSet<>();
    
    public Event() {
    }

    public Event(Integer id, String name, String startDate, String endDate) {
    	this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public void addParticipant(User user) {
    	users.add(user);
    }
    
    public boolean hasConflict(Event event) {
	  	Date startDate1 = new Date(this.getStartDate());
	  	Date endDate1 = new Date(this.getEndDate());
	  	Date startDate2 = new Date(event.getStartDate());
	  	Date endDate2 = new Date(event.getEndDate());
	  	
	  	if(endDate1.isBefore(startDate2)) return false;
	  	else if(startDate1.isAfter(endDate2)) return false;
	  	else return true;
    }
    
    public boolean valid() {
    	return (new Date(getStartDate())).isValid() && (new Date(getEndDate())).isValid();
    }
    
    //getters and setters
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
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

	@JsonIgnore
	@ManyToMany(mappedBy = "assignedEvents")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}