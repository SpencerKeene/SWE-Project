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
    public void register(@RequestBody User user) {
        userService.saveUser(user);
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
    public ResponseEntity<?> assignEvent(@RequestBody Map<String, Object> json) {
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
        	   
            	return new ResponseEntity<>(HttpStatus.OK);
           }
    	} catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
	@PutMapping("/change-password")
	public void changePassword(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@PutMapping("/change-email")
	public void changeEmail(@RequestBody Map<String, String> json) {
		String newEmail = json.get("newEmail");
		String oldEmail = json.get("oldEmail");
		User user = userService.getUser(oldEmail);
		user.setEmail(newEmail);
		userService.deleteUser(oldEmail);
		userService.saveUser(user);
	}
	
    @DeleteMapping("")
    public void delete(@RequestBody String email) {
        userService.deleteUser(email);
    }
    
    @DeleteMapping("/events")
    public ResponseEntity<?> removeEvent(@RequestBody Map<String, Object> json) {
    	String email = (String)json.get("email");
    	Integer eventId = (Integer)json.get("eventId");
    	Event event = eventService.getEvent(eventId);
    	
    	try {
    		User user = userService.getUser(email);
    		user.removeEvent(event);
    		userService.saveUser(user);
    		return new ResponseEntity<>(HttpStatus.OK);
    	} catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
}
