package com.federico_ioan.ProgettoIng.CourseModule;

import com.federico_ioan.ProgettoIng.Course.Course;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CourseModule")
public class CourseModule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
	private String name;

    @Column()
	private String description;

	@ManyToOne()
	@JoinColumn(name = "course_id")
	private Course course;

    @Column(nullable = false)
	private String url;

    @Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime created_date;

    @Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime updated_date;

	public CourseModule() {}

	public CourseModule(String name, String description, String url) {
		this.name = name;
		this.description = description;
		this.url = url;
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

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
