package com.calendar.databaseapi.controller;

//import com.calendar.databaseapi.model.Event;
import com.calendar.databaseapi.model.Event;
import com.calendar.databaseapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;
    
    @GetMapping("")
    public List<Event> list() {
        return eventService.listAllEvent();
    }
    
    @PostMapping("/")
    public void add(@RequestBody Event event) {
        eventService.saveEvent(event);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        eventService.deleteEvent(id);
    }
}
