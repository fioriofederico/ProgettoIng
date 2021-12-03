package com.federico_ioan.ProgettoIng.Course;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	private final CourseRepository courseRepository;
	
	CourseController(CourseRepository repository){courseRepository = repository;}
	
	@GetMapping()
	Iterable<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	@GetMapping("/{courseId}")
	Course getCourse(@PathVariable Long courseId){
		return courseRepository.findById(courseId).orElseThrow();
	}
	
	@PostMapping()
	Course createCourse(@RequestBody Course newCourse) {
		return courseRepository.save(newCourse);
	}
	
	@PutMapping("/{courseId}")
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
	
	@DeleteMapping("/{courseId}")
	Course deleteCourse(@PathVariable Long courseId){
		Course course = courseRepository.findById(courseId).orElseThrow();
		courseRepository.delete(course);
		return course;
	}
	
}
