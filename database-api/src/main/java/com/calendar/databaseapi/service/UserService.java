package com.calendar.databaseapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendar.databaseapi.model.User;
import com.calendar.databaseapi.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findById(email).get();
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }
    
    public boolean userExists(String email) {
    	return userRepository.existsById(email);
    }
}
