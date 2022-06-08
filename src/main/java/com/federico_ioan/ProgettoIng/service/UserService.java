package com.federico_ioan.ProgettoIng.service;

import com.federico_ioan.ProgettoIng.model.User;
import com.federico_ioan.ProgettoIng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }


    public ResponseEntity<User> getUser(Long userId) {
        try {
            return ResponseEntity.ok(userRepository.findById(userId).orElseThrow(Exception::new));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public User updatePwd(@PathVariable Long userId, @RequestBody User userDto){
        User userToUpdate = userRepository.findById(userId).orElseThrow();
        if(userDto.getPassword()!= null) {
            userToUpdate.setPassword(userDto.getPassword());
        }
        return userRepository.save(userToUpdate);
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
        return user;
    }


}
