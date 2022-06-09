package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.dto.UserLoginDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserRegistrationDto;
import com.ProgettoIng.FedericoIoan.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto loginRequest) {
		return authService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto signUpRequest) {
		return authService.registerUser(signUpRequest);
	}

	// TODO: add forgot password API function -> send email with reset password link

	// TODO: add reset password API function
}
