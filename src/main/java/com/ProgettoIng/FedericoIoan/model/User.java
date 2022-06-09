package com.ProgettoIng.FedericoIoan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String surname;

	private String username;

	private String email;

	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateUpdate;

	/**
	 * Constructor used during registration
	 * @param name
	 * @param surname
	 * @param username
	 * @param email
	 * @param encodedPassword
	 */
	public User(String name, String surname, String username, String email, String encodedPassword) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = encodedPassword;
	}
}
