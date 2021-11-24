package com.calendar.databaseapi.controller;

import com.calendar.databaseapi.model.Event;
import com.calendar.databaseapi.model.User;
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

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> get(@PathVariable String email) {
        try {
            User user = userService.getUser(email);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{email}")
    public ResponseEntity<ArrayList<Event>> events(@PathVariable String email){
    	try{
    		User user = userService.getUser(email);
    		return new ResponseEntity<ArrayList<Event>>(user.getEvents(), HttpStatus.OK);
    	} catch (NoSuchElementException e) {
            return new ResponseEntity<ArrayList<Event>>(HttpStatus.NOT_FOUND);
        }
    	
    }
    
    @PostMapping("/{email}/assign-event")
    public ResponseEntity<?> assignEvent(@PathVariable String email, @RequestBody Event event) {
    	if (!event.isValid()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	
    	try {
           User user = userService.getUser(email);
           if (user.isConflict(event)) return new ResponseEntity<>(HttpStatus.CONFLICT);
           else {
        	   user.assignEvent(event);
        	   return new ResponseEntity<>(HttpStatus.OK);
           }
    	} catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> json) {
    	String email = json.get("email");
    	String password = json.get("password");
    	
    	try {
            User user = userService.getUser(email);
            if (user.getPassword().equals(password)) return new ResponseEntity<>(HttpStatus.ACCEPTED);
            else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/")
    public void add(@RequestBody User user) {
        userService.saveUser(user);
    }
    @PutMapping("/{email}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable String email) {
    	if(userService.userExists(email)) {
    	   	user.setEmail(email);            
       		userService.saveUser(user);
       		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {

        userService.deleteUser(email);
    }
}