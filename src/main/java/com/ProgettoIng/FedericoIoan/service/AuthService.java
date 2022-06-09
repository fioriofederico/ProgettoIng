package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.ERole;
import com.ProgettoIng.FedericoIoan.model.Role;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.JwtResponse;
import com.ProgettoIng.FedericoIoan.model.dto.UserDetailsImpl;
import com.ProgettoIng.FedericoIoan.model.dto.UserLoginDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserRegistrationDto;
import com.ProgettoIng.FedericoIoan.repository.RoleRepository;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthService {

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

    public ResponseEntity<?> authenticateUser(UserLoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> registerUser(UserRegistrationDto signUpRequest) {

        // Check if username already exists
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            return ResponseEntity.badRequest().body("Error: Username is already taken!");

        // Check if email already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            return ResponseEntity.badRequest().body("Error: Email is already in use!");

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null)
            return ResponseEntity.badRequest().body("Error: Roles field is missing!");

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

        return ResponseEntity.ok("User registered successfully!");
    }
}