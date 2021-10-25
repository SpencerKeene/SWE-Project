package com.calendar.databaseapi.controller;

import com.calendar.databaseapi.model.Event;
import com.calendar.databaseapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping("")
    public List<Event> list() {
        return eventService.listAllEvent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Integer id) {
        try {
            Event event = eventService.getEvent(id);
            return new ResponseEntity<Event>(event, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Event event) {
        eventService.saveEvent(event);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Event event, @PathVariable Integer id) {
    	if(eventService.eventExists(id)) {
    	   	event.setId(id);            
       		eventService.saveEvent(event);
       		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        eventService.deleteEvent(id);
    }
}