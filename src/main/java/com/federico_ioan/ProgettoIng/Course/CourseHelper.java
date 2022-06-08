package com.federico_ioan.ProgettoIng.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseHelper {

    @Autowired
    private CourseRepository courseRepository;

    protected List<Course> findCourses() {
        return courseRepository.findAll();
    }

    protected Course findCourse(Long id) {
        if(courseRepository.existsById(id))
            return courseRepository.findById(id).get();
        return null;
    }

    protected Course createCourse(Course course) {
        if (course.getName() != null)
            return courseRepository.save(course);
        else
            return null;
    }

    protected Course updateCourse(Course course) {
        return courseRepository.save(
                new Course(course.getId(),
                        course.getName(),
                        course.getDuration(),
                        course.getOwner(),
                        course.getDateInsert(),
                        course.getDateUpdate())
        );
    }

    protected Course deleteCourse(Long id) {
        if(courseRepository.existsById(id)) {
            Course courseToDelete = courseRepository.findById(id).get();
            courseRepository.delete(courseToDelete);
            return courseToDelete;
        }
        return null;
    }
}
