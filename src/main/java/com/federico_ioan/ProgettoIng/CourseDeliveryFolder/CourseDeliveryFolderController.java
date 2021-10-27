package com.federico_ioan.ProgettoIng.CourseDeliveryFolder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseDeliveryFolderController {
	
	private final CourseDeliveryFolderRepository courseDeliveryFolderRepository;
	
	CourseDeliveryFolderController(CourseDeliveryFolderRepository repository){
		courseDeliveryFolderRepository = repository;
	}
	
	@GetMapping("/delivery_folders")
	Iterable<CourseDeliveryFolder> getCourseDeliveryFolder(){
		return courseDeliveryFolderRepository.findAll();
	}
	
	@GetMapping("/delivery_folder/{deliveryId}")
	CourseDeliveryFolder getCourseDeliveryFolderById(@PathVariable Long deliveryId) {
		return courseDeliveryFolderRepository.findById(deliveryId).orElseThrow();
	}
	
	@GetMapping("/delivery_folders/{courseId}")
	CourseDeliveryFolder getCourseDeliveryFolderByCourse(@PathVariable Long courseId) {
		return courseDeliveryFolderRepository.findByCourseId(courseId);
	}
	
	@PostMapping("/delivery_folders")
	CourseDeliveryFolder createCourseDeliveryFolder(@RequestBody CourseDeliveryFolder newCourseDeliveryFolder) {
		return courseDeliveryFolderRepository.save(newCourseDeliveryFolder);
	}
	
	@PutMapping("/delivery_folders/{deliveryFoldersId}")
	CourseDeliveryFolder updateCourseDeliveryFolder(@PathVariable Long deliveryFoldersId, @RequestBody CourseDeliveryFolder courseDeliveryFolderDto) {
		CourseDeliveryFolder courseDeliveryFolderToUpdate = courseDeliveryFolderRepository.findById(deliveryFoldersId).orElseThrow();
		if(courseDeliveryFolderDto.getName()!= null) {
			courseDeliveryFolderToUpdate.setName(courseDeliveryFolderDto.getName());
		}
		if(courseDeliveryFolderDto.getCourseId()!= null) {
			courseDeliveryFolderToUpdate.setCourseId(courseDeliveryFolderDto.getCourseId());
		}
		if(courseDeliveryFolderDto.getStartTime()!= null) {
			courseDeliveryFolderToUpdate.setStartTime(courseDeliveryFolderDto.getStartTime());
		}
		if(courseDeliveryFolderDto.getEndTime()!= null) {
			courseDeliveryFolderToUpdate.setEndTime(courseDeliveryFolderDto.getEndTime());
		}
		return courseDeliveryFolderRepository.save(courseDeliveryFolderToUpdate);
	}
	
	@DeleteMapping("/delivery_folders/{deliveryFoldersId}")
	CourseDeliveryFolder deleteCourseDeliveryFolder(@PathVariable Long deliveryFoldersId){
		CourseDeliveryFolder courseDeliveryFolder = courseDeliveryFolderRepository.findById(deliveryFoldersId).orElseThrow();
		courseDeliveryFolderRepository.delete(courseDeliveryFolder);
		return courseDeliveryFolder;
	}
}
