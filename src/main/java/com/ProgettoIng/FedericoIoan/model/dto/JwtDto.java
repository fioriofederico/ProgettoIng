package com.ProgettoIng.FedericoIoan.model.dto;

import com.ProgettoIng.FedericoIoan.model.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class JwtDto {

	private String token;

	private String type = "Bearer";

	private Long id;

	private String username;

	private String email;

	@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="name")
	@JsonIdentityReference(alwaysAsId=true)
	private Set<Role> roles;

    public JwtDto(String jwt, Long id, String username, String email, Set<Role> roles) {
		this.token = jwt;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
    }
}
