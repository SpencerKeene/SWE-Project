package com.calendar.databaseapi.service;

import com.calendar.databaseapi.model.User;
import com.calendar.databaseapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public User getUser(String id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    
    public boolean userExists(String id) {
    	return userRepository.existsById(id);
    }
    
    
    
}