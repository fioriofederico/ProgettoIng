package com.federico_ioan.ProgettoIng.Course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.federico_ioan.ProgettoIng.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String duration;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	// TO DO: enrolled students

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateUpdate;
}
