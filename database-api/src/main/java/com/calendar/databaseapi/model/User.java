package com.calendar.databaseapi.model;


/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;

@Entity //save to a table
@Table(name = "users") // name of the table
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
    @ManyToMany
    @JoinTable(
    	name = "event-user",
    	joinColumns = @JoinColumn(name = "email"),
    	inverseJoinColumns = @JoinColumn(name = "eventID"))
    HashSet<Event> assignedEvents;
    
   public User() {
   }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    public boolean isConflict(Event newEvent) {
		for(Event event : assignedEvents) {
			if(event.isConflict(newEvent)) return true;
		}
		return false;
    }
    
    public void assignEvent(Event event) {
    	assignedEvents.add(event);
    }
    
//other setters and getters
    public ArrayList<Event> getEvents() {
    	ArrayList<Event> events = new ArrayList<Event>();
    	for(Event event : assignedEvents) {
			events.add(event);
		}
    	return events;
    }
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    @Id
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
    
}
