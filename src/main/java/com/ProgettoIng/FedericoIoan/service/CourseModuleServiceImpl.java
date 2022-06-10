package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.dto.CourseModuleDto;
import com.ProgettoIng.FedericoIoan.repository.CourseModuleRepository;
import com.ProgettoIng.FedericoIoan.repository.CourseRepository;
import com.ProgettoIng.FedericoIoan.service.IService.CourseModuleService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseModuleServiceImpl implements CourseModuleService {

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseModule createCourseModule(CourseModuleDto courseModule) {

        CourseModule courseModuleToCreate = new CourseModule();

        courseModuleToCreate.setName(courseModule.getName());
        courseModuleToCreate.setDescription(courseModule.getDescription());

        try {
            Course course = courseRepository.findById(courseModule.getCourseId()).orElseThrow(Exception::new);
            courseModuleToCreate.setCourse(course);

            return courseModuleRepository.save(courseModuleToCreate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CourseModule> findCourseModules(Long courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(Exception::new);
            return courseModuleRepository.findCourseModulesByCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CourseModule findCourseModule(Long id) {
        try {
            return courseModuleRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CourseModule updateCourseModule(Long id, CourseModuleDto courseModule) {
        // Check if course module exists
        if (!courseModuleRepository.existsById(id))
            throw new RuntimeException("CourseModule with id " + id + " does not exist");

        // Get course module to update
        CourseModule courseModuleToUpdate = courseModuleRepository.findById(id).get();

        // Update course module
        courseModuleToUpdate.setName(courseModule.getName());
        courseModuleToUpdate.setDescription(courseModule.getDescription());

        return courseModuleRepository.save(courseModuleToUpdate);
    }

    public CourseModule deleteCourseModule(Long id) {
        // Check if course module exists
        if (!courseModuleRepository.existsById(id))
            throw new RuntimeException("CourseModule with id " + id + " does not exist");

        // Delete course module
        CourseModule courseModuleToDelete = courseModuleRepository.findById(id).get();
        courseModuleRepository.delete(courseModuleToDelete);
        return courseModuleToDelete;
    }
}
