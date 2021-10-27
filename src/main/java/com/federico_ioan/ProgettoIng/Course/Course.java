package com.federico_ioan.ProgettoIng.Course;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Course {
	private @Id @GeneratedValue Long id;
	private @Column(nullable = false) String name;
	private @Column(nullable = false) Long userId;
	private String duration;
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



	public LocalDateTime getDateInsert() {
		return dateInsert;
	}



	public void setDateInsert(LocalDateTime dateInsert) {
		this.dateInsert = dateInsert;
	}



	public Course() {}



	public Course(Long id, String name, Long userId, String duration, LocalDateTime dateInsert) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.duration = duration;
		this.dateInsert = dateInsert;
	}


	
}
