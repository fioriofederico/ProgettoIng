package com.federico_ioan.ProgettoIng.Course;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	private final CourseRepository courseRepository;
	
	CourseController(CourseRepository repository){
		courseRepository = repository;
	}
	
	@GetMapping("/courses")
	Iterable<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	@GetMapping("/courses/{courseId}")
	Course getCourse(@PathVariable Long courseId){
		return courseRepository.findById(courseId).orElseThrow();
	}
	
	@PostMapping("/courses")
	Course createCourse(@RequestBody Course newCourse) {
		return courseRepository.save(newCourse);
	}
	
	@PutMapping("/courses/{courseId}")
	Course updateCourse(@PathVariable Long courseId, @RequestBody Course courseDto) {
		Course courseToUpdate = courseRepository.findById(courseId).orElseThrow();
		if(courseDto.getName()!= null) {
			courseToUpdate.setName(courseDto.getName());
		}
		if(courseDto.getDuration()!= null) {
			courseToUpdate.setDuration(courseDto.getDuration());
		}
		if(courseDto.getUserId()!= null) {
			courseToUpdate.setUserId(courseDto.getUserId());
		}
		return courseRepository.save(courseToUpdate);
	}
	
	@DeleteMapping("/courses/{courseId}")
	Course deleteCourse(@PathVariable Long courseId){
		Course course = courseRepository.findById(courseId).orElseThrow();
		courseRepository.delete(course);
		return course;
	}
	
}
