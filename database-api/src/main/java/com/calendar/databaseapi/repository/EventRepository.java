package com.calendar.databaseapi.repository;

import com.calendar.databaseapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
