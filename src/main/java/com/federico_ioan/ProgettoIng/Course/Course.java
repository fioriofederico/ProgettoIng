package com.federico_ioan.ProgettoIng.Course;

import com.federico_ioan.ProgettoIng.CourseModule.CourseModule;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(min = 3)
	private String name;

	@NotBlank
	private Long userId;	// TO DO: one to many relation

	// TO DO: add many to many relation for enrolled users

	private String duration;

	@OneToMany(
			mappedBy = "course",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
//			orphanRemoval = true
	)
	private Set<CourseModule> courseModules;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateUpdate;

	public Course() {}

	public Course(String name, Long userId, String duration) {
		this.name = name;
		this.userId = userId;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Set<CourseModule> getCourseModules() {
		return courseModules;
	}

	public void setCourseModules(Set<CourseModule> courseModules) {
		this.courseModules = courseModules;
	}

	public LocalDateTime getDateInsert() {
		return dateInsert;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}
}
