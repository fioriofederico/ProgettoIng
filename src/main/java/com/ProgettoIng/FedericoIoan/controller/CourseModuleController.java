package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.dto.CourseModuleDto;
import com.ProgettoIng.FedericoIoan.service.CourseModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course_modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleServiceImpl courseModuleService;

    @PostMapping
    public ResponseEntity<CourseModule> createCourseModule(@Valid @RequestBody CourseModuleDto courseModule) {
        CourseModule createdCourseModule = courseModuleService.createCourseModule(courseModule);
        return ResponseEntity.ok(createdCourseModule);
    }

    @GetMapping
    public ResponseEntity<List<CourseModule>> getCourseModules() {
        List<CourseModule> courseModules = courseModuleService.findCourseModules();
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
}
