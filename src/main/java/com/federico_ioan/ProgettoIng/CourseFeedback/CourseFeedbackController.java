package com.federico_ioan.ProgettoIng.CourseFeedback;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseFeedbackController {
	private final CourseFeedbackRepository courseFeedbackRepository;
	
	CourseFeedbackController(CourseFeedbackRepository repository){
		courseFeedbackRepository = repository;
	}
	
	@GetMapping("/courses_feedback/{courseId}")
	CourseFeedback getCourseFeedback(@PathVariable Long courseId){
		return courseFeedbackRepository.findByCourseId(courseId);
	}
	
	@PostMapping("/courses_feedback")
	CourseFeedback createCourseFeedback(@RequestBody CourseFeedback newCourseFeedback) {
		return courseFeedbackRepository.save(newCourseFeedback);
	}
	
	@PutMapping("/courses_feedback/{feedbackId}")
	CourseFeedback updateCourseFeedback(@PathVariable Long feedbackId, @RequestBody CourseFeedback courseFeedbackDto) {
		CourseFeedback courseFeedbackToUpdate = courseFeedbackRepository.findById(feedbackId).orElseThrow();
		if(courseFeedbackDto.getScore()!= null) {
			courseFeedbackToUpdate.setScore(courseFeedbackDto.getScore());
		}
		if(courseFeedbackDto.getDescription()!= null) {
			courseFeedbackToUpdate.setDescription(courseFeedbackDto.getDescription());
		}
		return courseFeedbackRepository.save(courseFeedbackToUpdate);
	}
	
	@DeleteMapping("/courses_feedback/{feedbackId}")
	CourseFeedback deleteCourseFeedback(@PathVariable Long feedbackId){
		CourseFeedback courseFeedback = courseFeedbackRepository.findById(feedbackId).orElseThrow();
		courseFeedbackRepository.delete(courseFeedback);
		return courseFeedback;
	}
}
