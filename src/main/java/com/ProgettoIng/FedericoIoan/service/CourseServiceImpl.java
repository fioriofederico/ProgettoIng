package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.CourseDto;
import com.ProgettoIng.FedericoIoan.repository.CourseRepository;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.service.IService.CourseService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    public List<Course> findCourses() {
        return courseRepository.findAll();
    }

    public List<Course> findTutorCourses(Long ownerId) {
        try {
            User owner = userRepository.findById(ownerId).orElseThrow(Exception::new);
            List<Course> courses = courseRepository.findCoursesByOwner(owner);
            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Course findCourse(Long id) {
        try {
            return courseRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Course createCourse(CourseDto course) {
        Course courseToCreate = new Course();

        courseToCreate.setName(course.getName());
        courseToCreate.setDescription(course.getDescription());

        try {
            User owner = userRepository.findById(course.getOwnerId()).orElseThrow(Exception::new);
            courseToCreate.setOwner(owner);

            return courseRepository.save(courseToCreate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Course updateCourse(Long id, CourseDto course) {
        // Check if the course exists
        if (!courseRepository.existsById(id))
            throw new RuntimeException("Course not found");

        // Get course to update
        Course courseToUpdate = courseRepository.findById(id).get();

        // Update course
        courseToUpdate.setName(course.getName());
        courseToUpdate.setDescription(course.getDescription());

        return courseRepository.save(courseToUpdate);
    }

    public Course deleteCourse(Long id) {
        Preconditions.checkArgument(!Objects.isNull(id));
        if (courseRepository.existsById(id)) {
            Course courseToDelete = courseRepository.findById(id).get();
            courseRepository.delete(courseToDelete);
            return courseToDelete;
        }
        return null;
    }
}
