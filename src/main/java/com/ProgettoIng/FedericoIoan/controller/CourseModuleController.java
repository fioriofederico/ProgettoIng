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

    @PostMapping
    public ResponseEntity<?> createCourseModule(@PathVariable Long courseId,
                                                @Valid @RequestBody CourseModuleDto courseModule) {
        try {
            CourseModule createdCourseModule = courseModuleService.createCourseModule(courseId, courseModule);
            return ResponseEntity.ok(createdCourseModule);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCourseModules(@PathVariable Long courseId) {
        try {
            List<CourseModule> courseModules = courseModuleService.findCourseModules(courseId);
            return ResponseEntity.ok(courseModules);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseModule(@PathVariable Long id) {
        try {
            CourseModule courseModule = courseModuleService.findCourseModule(id);
            return ResponseEntity.ok(courseModule);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourseModule(@PathVariable Long id,
                                                           @Valid @RequestBody CourseModuleDto courseModule) {
        try {
            CourseModule updatedCourseModule = courseModuleService.updateCourseModule(id, courseModule);
            return ResponseEntity.ok(updatedCourseModule);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseModule(@PathVariable Long id) {
        try {
            CourseModule deletedCourseModule = courseModuleService.deleteCourseModule(id);
            return ResponseEntity.ok(deletedCourseModule);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
