package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.ERole;
import com.ProgettoIng.FedericoIoan.model.Role;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.JwtDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserDetailsImpl;
import com.ProgettoIng.FedericoIoan.model.dto.UserLoginDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserRegistrationDto;
import com.ProgettoIng.FedericoIoan.repository.RoleRepository;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.service.IService.AuthService;
import com.ProgettoIng.FedericoIoan.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtDto authenticateUser(UserLoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtDto(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    public User registerUser(UserRegistrationDto signUpRequest) {

        // Check if username already exists
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new RuntimeException("Username already exists");

        // Check if email already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new RuntimeException("Email already exists");

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null)
            throw new RuntimeException("User must have at least one role");

        // Check if roles are valid
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "tutor":
                    Role modRole = roleRepository.findByName(ERole.TUTOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);

                    break;
                case "student":
                    Role userRole = roleRepository.findByName(ERole.STUDENT)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);

                    break;
                default:
                    throw new RuntimeException("Error: Role is not valid.");
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}