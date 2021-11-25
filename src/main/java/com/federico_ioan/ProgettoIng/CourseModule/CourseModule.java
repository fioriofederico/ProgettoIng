package com.federico_ioan.ProgettoIng.CourseModule;

import com.federico_ioan.ProgettoIng.Course.Course;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Table(name = "courses_modules")
public class CourseModule {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
	private String name;

	@Size(max = 256)
	private String description;

	@ManyToOne(
			fetch = FetchType.LAZY,
			optional = false
	)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

    @Column(nullable = false)
	private String url;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateInsert;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateUpdate;

	public CourseModule() {}

	public CourseModule(String name, String description, String url) {
		this.name = name;
		this.description = description;
		this.url = url;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getDateInsert() {
		return dateInsert;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}
}
