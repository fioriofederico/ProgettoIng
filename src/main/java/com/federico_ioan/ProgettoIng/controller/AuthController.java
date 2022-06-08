package com.federico_ioan.ProgettoIng.controller;

import com.federico_ioan.ProgettoIng.service.AuthService;
import com.federico_ioan.ProgettoIng.model.dto.LoginRequest;
import com.federico_ioan.ProgettoIng.model.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
