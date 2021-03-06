package com.calendar.databaseapi.controller;

import com.calendar.databaseapi.model.Event;
import com.calendar.databaseapi.model.User;
import com.calendar.databaseapi.service.EventService;
import com.calendar.databaseapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = {"http://147.182.152.27", "http://mystudentaccount.me"})
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    
    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }
    
    // TODO - test
    @GetMapping("/free-time")
    public ResponseEntity<ArrayList<String>> freeTime(@RequestBody String email){
    	try {
    		User user = userService.getUser(email);
    		return new ResponseEntity<ArrayList<String>>(user.freeTime(), HttpStatus.OK);
    	} catch (NoSuchElementException e) {
            return new ResponseEntity<ArrayList<String>>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<User> register(@RequestBody User user) {
    	if (userService.userExists(user.getEmail())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    	
        userService.saveUser(user);
        try {
        	User userAccount = userService.getUser(user.getEmail());
        	return new ResponseEntity<User>(userAccount, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
        	return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> json) {
    	String email = json.get("email");
    	String password = json.get("password");
    	
    	try {
            User user = userService.getUser(email);
            if (user.getPassword().equals(password))
            	return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
            else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // TODO - conflict check is not working properly
	@PostMapping("/events")
    public ResponseEntity<Event> assignEvent(@RequestBody Map<String, Object> json) {
    	String email = (String)json.get("email");
    	@SuppressWarnings("unchecked")
		Map<String, Object> eventComponents = (Map<String, Object>)json.get("event");
    	Event event = null;
    	try {
    		Integer eventId = (Integer)eventComponents.get("id");
    		if (eventId == null) throw new NoSuchElementException();
    		event = eventService.getEvent(eventId);
    	} catch (NoSuchElementException e) {
    		event = new Event(0,
        			(String)eventComponents.get("name"), 
        			(String)eventComponents.get("startDate"), 
        			(String)eventComponents.get("endDate"));
    		
    		if (!event.valid()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	try {
    		User user = userService.getUser(email);
    		
            if (user.hasConflict(event))
            	return new ResponseEntity<>(HttpStatus.CONFLICT);
            else {
            	eventService.saveEvent(event);
        	   
            	user.assignEvent(event);
            	userService.saveUser(user);
        	   
            	return new ResponseEntity<Event>(event, HttpStatus.CREATED);
           }
    	} catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody Map<String, String> json) {
		String email = json.get("email");
		String newPassword = json.get("newPassword");
		
		try {
			User user = userService.getUser(email);
			user.setPassword(newPassword);
			userService.saveUser(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/change-email")
	public ResponseEntity<?> changeEmail(@RequestBody Map<String, String> json) {
		String newEmail = json.get("newEmail");
		String oldEmail = json.get("oldEmail");

		if (userService.userExists(newEmail)) return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		try {
			User user = userService.getUser(oldEmail);
			User newUser = new User(user.getFirstName(), user.getLastName(), newEmail, user.getPassword());
			newUser.setAssignedEvents(user.getAssignedEvents());
			
			userService.saveUser(newUser);
			userService.deleteUser(oldEmail);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
    @DeleteMapping("")
    public void delete(@RequestBody String email) {
        userService.deleteUser(email);
    }
    
    @DeleteMapping("/events")
    public ResponseEntity<?> removeEvent(@RequestBody Map<String, Object> json) {
    	String email = (String)json.get("email");
    	Integer eventId = (Integer)json.get("eventId");
    	
    	try {
    		User user = userService.getUser(email);
    		Event event = eventService.getEvent(eventId);
    		
    		user.removeEvent(event);
    		userService.saveUser(user);
    		
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    	} catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
}
