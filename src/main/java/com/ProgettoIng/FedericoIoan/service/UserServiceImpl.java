package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.dto.NewPasswordDto;
import com.ProgettoIng.FedericoIoan.utils.SecurityUtils;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.service.IService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public List<User> findUsers(){
        return userRepository.findAll();
    }


    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User updatePwd( Long userId, NewPasswordDto password) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userToUpdate.setPassword(encoder.encode(password.getPassword()));

        return userRepository.save(userToUpdate);
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}