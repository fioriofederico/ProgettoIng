package com.federico_ioan.ProgettoIng.Course;

import com.federico_ioan.ProgettoIng.CourseModule.CourseModule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Course")
public class Course {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Long userId;

	private String duration;

	@OneToMany(
			mappedBy = "course",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<CourseModule> courseModules = new ArrayList<>();

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	public Course() {}

	public Course(String name, Long userId, String duration) {
		this.name = name;
		this.userId = userId;
		this.duration = duration;
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

	public void setCourseModules(List<CourseModule> courseModules) {
		this.courseModules = courseModules;
	}
}
