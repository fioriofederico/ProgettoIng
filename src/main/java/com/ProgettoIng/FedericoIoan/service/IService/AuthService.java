package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.JwtDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserLoginDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserRegistrationDto;


public interface AuthService {

    JwtDto authenticateUser(UserLoginDto loginRequest);

    User registerUser(UserRegistrationDto signUpRequest);
}
