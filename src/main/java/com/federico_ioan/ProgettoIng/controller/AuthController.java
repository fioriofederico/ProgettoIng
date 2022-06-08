package com.federico_ioan.ProgettoIng.controller;

import com.federico_ioan.ProgettoIng.model.User;
import com.federico_ioan.ProgettoIng.repository.UserRepository;
import com.federico_ioan.ProgettoIng.service.AuthService;
import com.federico_ioan.ProgettoIng.utils.JwtUtils;
import com.federico_ioan.ProgettoIng.service.UserDetailsImpl;
import com.federico_ioan.ProgettoIng.model.ERole;
import com.federico_ioan.ProgettoIng.model.Role;
import com.federico_ioan.ProgettoIng.model.dto.JwtResponse;
import com.federico_ioan.ProgettoIng.model.dto.LoginRequest;
import com.federico_ioan.ProgettoIng.model.dto.MessageResponse;
import com.federico_ioan.ProgettoIng.model.dto.SignupRequest;
import com.federico_ioan.ProgettoIng.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		return authService.registerUser(signUpRequest);
	}
}
