package com.calendar.databaseapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendar.databaseapi.model.Event;


public interface EventRepository extends JpaRepository<Event, Integer> {

}
