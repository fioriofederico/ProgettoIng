package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.utils.SecurityUtils;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.service.IService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findUsers(){
        return userRepository.findAll();
    }


    public User findUser(Long userId) {
        try {
            return userRepository.findById(userId).orElseThrow(Exception::new);
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

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

}
