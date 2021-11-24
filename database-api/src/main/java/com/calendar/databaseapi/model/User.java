package com.calendar.databaseapi.model;


/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import javax.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
    
    //returns an sorted string list of available times
    public ArrayList<String> freeTime(){
    	Event[] sortArray = new Event[assignedEvents.size()];
    	Iterator<Event> itr = assignedEvents.iterator();
    	Event event;
    	for(int i = 0; i < assignedEvents.size(); i++) {
    		sortArray[i] = itr.next();
    	}
    	Event temp;
    	boolean sorted = false;
    	while(!sorted) { //bubble sort :)
    		sorted = true;
    		for(int i = 0; i < sortArray.length; i++) {
    			if(sortArray[i].getStartDate().isAfter(sortArray[i+1].getStartDate())) {
    				temp = sortArray[i];
    				sortArray[i] = sortArray[i+1];
    				sortArray[i+1] = temp;
    				sorted = false;
    			}
    		}
    	}
    	ArrayList<String> freeTime = new ArrayList<String>();
    	freeTime.add("00:00 - " + sortArray[0].getStartDate().hour + ":" + sortArray[0].getStartDate().minute);
    	for(int i = 1; i < sortArray.length; i++) {
    		freeTime.add(sortArray[i-1].getEndDate().hour + ":" + sortArray[i-1].getEndDate().minute + " - " + sortArray[i].getStartDate().hour + ":" + sortArray[i].getStartDate().minute);
    	}
    	freeTime.add(sortArray[sortArray.length-1].getEndDate().hour + ":" + sortArray[sortArray.length-1].getEndDate().minute + " - " + "24:00");
    	return freeTime;
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
