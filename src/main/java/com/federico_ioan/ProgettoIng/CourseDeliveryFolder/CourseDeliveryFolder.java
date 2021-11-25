package com.federico_ioan.ProgettoIng.CourseDeliveryFolder;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class CourseDeliveryFolder {
	private @Id @GeneratedValue Long id;
	private @Column(nullable = false) String name;
	private @Column(nullable = false) Long courseId;	// TO DO: one to many relation
	private LocalDateTime startTime;
	private LocalDateTime endTime;
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



	public Long getCourseId() {
		return courseId;
	}



	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}



	public LocalDateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}



	public LocalDateTime getEndTime() {
		return endTime;
	}



	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}



	public LocalDateTime getDateInsert() {
		return dateInsert;
	}



	public void setDateInsert(LocalDateTime dateInsert) {
		this.dateInsert = dateInsert;
	}



	public CourseDeliveryFolder() {}



	public CourseDeliveryFolder(Long id, String name, Long courseId, LocalDateTime startTime, LocalDateTime endTime,
			LocalDateTime dateInsert) {
		super();
		this.id = id;
		this.name = name;
		this.courseId = courseId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dateInsert = dateInsert;
	}


	

}
