package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.User;
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

	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id,
											   @Valid @RequestBody CourseDto course) {
		Course updatedCourse = courseService.updateCourse(id, course);
		return ResponseEntity.ok(updatedCourse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
		Course deletedCourse = courseService.deleteCourse(id);
		return ResponseEntity.ok(deletedCourse);
	}

	// Enroll a user in a course
	@PostMapping("{courseId}/enroll/{userId}")
	public ResponseEntity<User> enrollUser(@PathVariable Long courseId, @PathVariable Long userId) {
		User enrolledUser = courseService.enrollUser(courseId, userId);
		return ResponseEntity.ok(enrolledUser);
	}
}
