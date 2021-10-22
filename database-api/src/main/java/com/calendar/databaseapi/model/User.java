package com.calendar.databaseapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "users")

public class User {
	 private int id;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String password;

	    public User() {
	    }

	    public User(int id, String firstName, String lastName
	    		, String email, String password) {
	        this.id = id;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	    }
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public int getId() {
	        return id;
	    }
}
