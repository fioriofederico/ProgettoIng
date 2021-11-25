package com.federico_ioan.ProgettoIng.CourseModule;

import com.federico_ioan.ProgettoIng.Course.Course;
import com.federico_ioan.ProgettoIng.Course.CourseRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/{courseId}/modules")
public class CourseModuleController {

    private final CourseRepository courseRepository;
    private final CourseModuleRepository courseModuleRepository;

    CourseModuleController(CourseRepository courseRepository, CourseModuleRepository courseModuleRepository) {
        this.courseRepository = courseRepository;
        this.courseModuleRepository = courseModuleRepository;
    }

    @GetMapping()
    Iterable<CourseModule> getCoursesModules(){
        return courseModuleRepository.findAll();
    }

    @GetMapping("/{courseModuleId}")
    CourseModule getCourseModule(@PathVariable Long courseModuleId){
        return courseModuleRepository.findById(courseModuleId).orElseThrow();
    }

    @PostMapping()
    CourseModule createCourseModule(@PathVariable Long courseId, @RequestBody CourseModule newCourseModule) {
        if( courseRepository.existsById(courseId) ) {
            Course courseToUpdate = courseRepository.findCourseById(courseId);
            newCourseModule.setCourse(courseToUpdate);
            courseModuleRepository.save(newCourseModule);
            return newCourseModule;
        }
        return null;
    }

    @PutMapping("/{courseModuleId}")
    CourseModule updateCourseModule(@PathVariable Long courseModuleId, @RequestBody CourseModule courseModuleDto) {
        CourseModule courseModuleToUpdate = courseModuleRepository.findById(courseModuleId).orElseThrow();
        if(courseModuleDto.getName()!= null) {
            courseModuleToUpdate.setName(courseModuleDto.getName());
        }
        if(courseModuleDto.getDescription()!= null) {
            courseModuleToUpdate.setDescription(courseModuleDto.getDescription());
        }
        if(courseModuleDto.getUrl()!= null) {
            courseModuleToUpdate.setUrl(courseModuleDto.getUrl());
        }
        return courseModuleRepository.save(courseModuleToUpdate);
    }

    @DeleteMapping("/{courseModuleId}")
    CourseModule deleteCourseModule(@PathVariable Long courseModuleId){
        CourseModule courseModule = courseModuleRepository.findById(courseModuleId).orElseThrow();
        courseModuleRepository.delete(courseModule);
        return courseModule;
    }
}
