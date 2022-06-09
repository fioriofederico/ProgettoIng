package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.dto.CourseDto;
import com.ProgettoIng.FedericoIoan.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseServiceImpl courseService;

	@GetMapping
	public ResponseEntity<List<Course>> getCourses() {
		List<Course> courses = courseService.findCourses();
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable Long id) {
		Course course = courseService.findCourse(id);
		return ResponseEntity.ok(course);
	}

	@PostMapping
	public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDto course) {
		Course createdCourse = courseService.createCourse(course);
		return ResponseEntity.ok(createdCourse);
	}

	@PutMapping
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		Course updatedCourse = courseService.updateCourse(course);
		return ResponseEntity.ok(updatedCourse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
		Course deletedCourse = courseService.deleteCourse(id);
		return ResponseEntity.ok(deletedCourse);
	}
}
