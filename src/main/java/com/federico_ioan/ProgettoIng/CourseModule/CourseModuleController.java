package com.federico_ioan.ProgettoIng.CourseModule;

import com.federico_ioan.ProgettoIng.Course.Course;
import com.federico_ioan.ProgettoIng.Course.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class CourseModuleController {

    private final CourseRepository courseRepository;

    CourseModuleController(CourseRepository repository) {
        courseRepository = repository;
    }

    @PostMapping("/courses/{courseId}/modules")
    CourseModule createCourseModule(@PathVariable Long courseId, @RequestBody CourseModule newCourseModule) {
        // Check if course exist
        if( courseRepository.existsById(courseId) ) {
            // Find the course to update
            Course courseToUpdate = courseRepository.findById(courseId).orElseThrow();
            // Set the the course on the new module
            newCourseModule.setCourse(courseToUpdate);
            // Add the module in the course
            courseToUpdate.setCourseModules(Arrays.asList(newCourseModule));
            // Save
            courseRepository.save(courseToUpdate);
            return newCourseModule;
        }
        return null;
    }
}
