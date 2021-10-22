package com.calendar.databaseapi.repository;

import com.calendar.databaseapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
}