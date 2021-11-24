package com.calendar.databaseapi.repository;

import com.calendar.databaseapi.model.Event;
import com.calendar.databaseapi.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
	List<Event> findAllByEmail(String email);
}
