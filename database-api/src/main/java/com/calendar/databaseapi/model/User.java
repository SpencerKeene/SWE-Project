package com.calendar.databaseapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Event> assignedEvents = new HashSet<Event>();
    
    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    public void assignEvent(Event event) {
    	assignedEvents.add(event);
    }
    
    public void removeEvent(Event event) {
    	assignedEvents.remove(event);
    }
    
    public boolean hasConflict(Event event) {
		for(Event otherEvent : getAssignedEvents()) {
			if(otherEvent.hasConflict(event)) return true;
		}
		return false;
    }
    
    // TODO - fix mess of new Date() everywhere
    public ArrayList<String> freeTime(){
    	Event[] sortArray = new Event[assignedEvents.size()];
    	Iterator<Event> itr = assignedEvents.iterator();
    	for(int i = 0; i < assignedEvents.size(); i++) {
    		sortArray[i] = itr.next();
    	}
    	
    	//array sort method
    	Arrays.sort(sortArray, new Comparator<Event>(){
    		@Override
    		public int compare(Event first, Event second) {
    			if(new Date(first.getStartDate()).isAfter(new Date(second.getStartDate()))) return 1;
    			else if(new Date(first.getStartDate()).isBefore(new Date(second.getStartDate()))) return -1;
    			else return 0;
    		}
    	});
    	/*bubble sort method
    	Event temp;
    	boolean sorted = false;
    	while(!sorted) { //bubble sort :)
    		sorted = true;
    		for(int i = 0; i < sortArray.length; i++) {
    			if(new Date(sortArray[i].getStartDate()).isAfter(new Date(sortArray[i+1].getStartDate()))) {
    				temp = sortArray[i];
    				sortArray[i] = sortArray[i+1];
    				sortArray[i+1] = temp;
    				sorted = false;
    			}
    		}
    	}
    	}*/
    	ArrayList<String> freeTime = new ArrayList<String>();
    	freeTime.add("00:00 - " + new Date(sortArray[0].getStartDate()).hour + ":" + new Date(sortArray[0].getStartDate()).minute);
    	for(int i = 1; i < sortArray.length; i++) {
    		freeTime.add(new Date(sortArray[i-1].getEndDate()).hour + ":" + new Date(sortArray[i-1].getEndDate()).minute + " - " +
    				new Date(sortArray[i].getStartDate()).hour + ":" + new Date(sortArray[i].getStartDate()).minute);
    	}
    	freeTime.add(new Date(sortArray[sortArray.length-1].getEndDate()).hour + ":" + new Date(sortArray[sortArray.length-1].getEndDate()).minute + " - " + "24:00");
    	return freeTime;
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
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}

    @ManyToMany
    @JoinTable(
    	name = "event_user",
    	joinColumns = @JoinColumn(name = "user_email"),
    	inverseJoinColumns = @JoinColumn(name = "event_id"))
	public Set<Event> getAssignedEvents() {
		return assignedEvents;
	}

	public void setAssignedEvents(Set<Event> assignedEvents) {
		this.assignedEvents = assignedEvents;
	}
}
