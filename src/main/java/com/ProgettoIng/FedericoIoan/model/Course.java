package com.ProgettoIng.FedericoIoan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String duration;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnore
	private User owner;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "enrolled_users",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonBackReference(value = "enrolledUsers")
	private Set<User> enrolledUsers = new HashSet<>();

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateUpdate;
}