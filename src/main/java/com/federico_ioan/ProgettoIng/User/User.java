package com.federico_ioan.ProgettoIng.User;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	private @Id @GeneratedValue Long id;
	private @Column(nullable = false) String name;
	private @Column(nullable = false) String surname;
	private @Column(nullable = false, unique=true) String email;
	private @Column(nullable = false) String role;
	private @Column(nullable = false) String password;
	private @Column(columnDefinition = "TIMESTAMP") LocalDateTime dateInsert;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(LocalDateTime dateInsert) {
		this.dateInsert = dateInsert;
	}

	public User(){}

	public User(Long id, String name, String surname, String email, String role, String password,
			LocalDateTime dateInsert) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = role;
		this.password = password;
		this.dateInsert = dateInsert;
	}
	
}
