package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtDto {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

    public JwtDto(String jwt, Long id, String username, String email, List<String> roles) {
		this.token = jwt;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
    }
}
