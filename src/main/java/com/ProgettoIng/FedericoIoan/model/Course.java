package com.ProgettoIng.FedericoIoan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	@OneToMany(mappedBy = "course")
	@JsonIgnore
	Set<CourseEnrollment> enrolledUsers;


	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateUpdate;
}
