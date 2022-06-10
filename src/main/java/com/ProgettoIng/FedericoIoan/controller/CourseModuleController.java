package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.VideoDetails;
import com.ProgettoIng.FedericoIoan.model.dto.CourseModuleDto;
import com.ProgettoIng.FedericoIoan.service.CourseModuleServiceImpl;
import com.ProgettoIng.FedericoIoan.service.VideoDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleServiceImpl courseModuleService;

    @Autowired
    private VideoDetailsServiceImpl videoDetailsService;

    @PostMapping
    public ResponseEntity<CourseModule> createCourseModule(@Valid @RequestBody CourseModuleDto courseModule) {
        CourseModule createdCourseModule = courseModuleService.createCourseModule(courseModule);
        return ResponseEntity.ok(createdCourseModule);
    }

    @GetMapping
    public ResponseEntity<List<CourseModule>> getCourseModules(@PathVariable Long courseId) {
        List<CourseModule> courseModules = courseModuleService.findCourseModules(courseId);
        return ResponseEntity.ok(courseModules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseModule> getCourseModule(@PathVariable Long id) {
        CourseModule courseModule = courseModuleService.findCourseModule(id);
        return ResponseEntity.ok(courseModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseModule> updateCourseModule(@PathVariable Long id,
                                                           @Valid @RequestBody CourseModuleDto courseModule) {
        CourseModule updatedCourseModule = courseModuleService.updateCourseModule(id, courseModule);
        return ResponseEntity.ok(updatedCourseModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseModule> deleteCourseModule(@PathVariable Long id) {
        CourseModule deletedCourseModule = courseModuleService.deleteCourseModule(id);
        return ResponseEntity.ok(deletedCourseModule);
    }

    // Upload video to course module
    @PostMapping("/{id}/upload_video")
    public ResponseEntity<?> uploadVideo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        VideoDetails videoDetails = videoDetailsService.uploadVideo(id, file);
        return ResponseEntity.ok(videoDetails);
    }

    // Delete video from course module
    @DeleteMapping("/{id}/delete_video")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id) {
        VideoDetails videoDetails = videoDetailsService.deleteVideo(id);
        return ResponseEntity.ok(videoDetails);
    }

}
