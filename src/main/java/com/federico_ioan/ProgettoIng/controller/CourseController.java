package com.federico_ioan.ProgettoIng.controller;

import com.federico_ioan.ProgettoIng.model.dto.CourseDto;
import com.federico_ioan.ProgettoIng.service.CourseServiceImpl;
import com.federico_ioan.ProgettoIng.utils.ResponseHandler;
import com.federico_ioan.ProgettoIng.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Object> getCourses() {
		try {
			List<Course> courses = courseService.findCourses();
			return ResponseHandler.generateResponse("Successfully retrived data!", HttpStatus.OK, courses);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCourse(@PathVariable Long id) {
		try {
			Course course = courseService.findCourse(id);
			return ResponseHandler.generateResponse("Successfully retrived data!", HttpStatus.OK, course);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@PostMapping
	public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseDto course) {
		System.out.println("ciao");
		try {
			//1 get
			Course createdCourse = new Course();
			return ResponseHandler.generateResponse("Successfully uploaded data!", HttpStatus.OK, createdCourse);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@PutMapping
	public ResponseEntity<Object> updateCourse(@RequestBody Course course) {
		try {
			Course updatedCourse = courseService.updateCourse(course);
			return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, updatedCourse);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
		try {
			Course deletedCourse = courseService.deleteCourse(id);
			return ResponseHandler.generateResponse("Successfully retrived data!", HttpStatus.OK, deletedCourse);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}


	
}
