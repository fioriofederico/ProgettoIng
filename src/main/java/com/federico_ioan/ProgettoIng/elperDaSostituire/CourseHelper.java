package com.federico_ioan.ProgettoIng.elperDaSostituire;

import com.federico_ioan.ProgettoIng.model.Course;
import com.federico_ioan.ProgettoIng.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseHelper {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findCourses() {
        return courseRepository.findAll();
    }

    public Course findCourse(Long id) {
        if(courseRepository.existsById(id))
            return courseRepository.findById(id).get();
        return null;
    }

    public Course createCourse(Course course) {
        if (course.getName() != null)
            return courseRepository.save(course);
        else
            return null;
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(
                new Course(course.getId(),
                        course.getName(),
                        course.getDuration(),
                        course.getOwner(),
                        course.getDateInsert(),
                        course.getDateUpdate())
        );
    }

    public Course deleteCourse(Long id) {
        if(courseRepository.existsById(id)) {
            Course courseToDelete = courseRepository.findById(id).get();
            courseRepository.delete(courseToDelete);
            return courseToDelete;
        }
        return null;
    }
}
