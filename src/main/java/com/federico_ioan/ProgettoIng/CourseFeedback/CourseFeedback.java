package com.federico_ioan.ProgettoIng.CourseFeedback;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class CourseFeedback {
	private @Id @GeneratedValue Long id;
	private @Column(nullable = false) Long score;
	private @Column(length = 4000) String description;
	private @Column(nullable = false) Long userId;
	private @Column(nullable = false) Long courseId;
	private @Column(columnDefinition = "TIMESTAMP") LocalDateTime dateInsert;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public LocalDateTime getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(LocalDateTime dateInsert) {
		this.dateInsert = dateInsert;
	}

	public CourseFeedback(){}

	public CourseFeedback(Long id, Long score, String description, Long userId, Long courseId,
			LocalDateTime dateInsert) {
		super();
		this.id = id;
		this.score = score;
		this.description = description;
		this.userId = userId;
		this.courseId = courseId;
		this.dateInsert = dateInsert;
	}

	
	
}
