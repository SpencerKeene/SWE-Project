package com.calendar.databaseapi.model;


/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import javax.persistence.*;

import org.springframework.http.ResponseEntity;

import java.util.Iterator;
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
    	joinColumns = @JoinColumn(name = "user_id"),
    	inverseJoinColumns = @JoinColumn(name = "event_id"))
    HashSet<Event> assignedEvents;
    
   public User() {
   }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        assignedEvents = new HashSet<>();
    }
    
    public boolean isConflict(Event newEvent) {
		for(Event event : assignedEvents) {
			if(event.isConflict(newEvent)) return true;
		}
		return false;
    }
    
//other setters and getters
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
