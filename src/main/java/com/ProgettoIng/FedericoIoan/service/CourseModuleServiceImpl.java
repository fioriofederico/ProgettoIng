package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.dto.CourseModuleDto;
import com.ProgettoIng.FedericoIoan.repository.CourseModuleRepository;
import com.ProgettoIng.FedericoIoan.repository.CourseRepository;
import com.ProgettoIng.FedericoIoan.service.IService.CourseModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseModuleServiceImpl implements CourseModuleService {

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseModule createCourseModule(Long courseId, CourseModuleDto courseModule) {

        CourseModule courseModuleToCreate = new CourseModule();

        courseModuleToCreate.setName(courseModule.getName());
        courseModuleToCreate.setDescription(courseModule.getDescription());

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseModuleToCreate.setCourse(course);

        return courseModuleRepository.save(courseModuleToCreate);
    }

    public List<CourseModule> findCourseModules(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseModuleRepository.findCourseModulesByCourse(course);
    }

    public CourseModule findCourseModule(Long id) {
        return courseModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course module not found"));
    }

    public CourseModule updateCourseModule(Long id, CourseModuleDto courseModule) {
        CourseModule courseModuleToUpdate = courseModuleRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Course module not found"));

        courseModuleToUpdate.setName(courseModule.getName());
        courseModuleToUpdate.setDescription(courseModule.getDescription());

        return courseModuleRepository.save(courseModuleToUpdate);
    }

    public CourseModule deleteCourseModule(Long id) {
        CourseModule courseModuleToDelete = courseModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course module not found"));

        courseModuleRepository.delete(courseModuleToDelete);
        return courseModuleToDelete;
    }
}