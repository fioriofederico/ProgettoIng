package com.federico_ioan.ProgettoIng.service;
import com.federico_ioan.ProgettoIng.model.Course;
import com.federico_ioan.ProgettoIng.repository.CourseRepository;
import com.federico_ioan.ProgettoIng.service.IService.CourseService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;
    public List<Course> findCourses() {
        return courseRepository.findAll();
    }

    public Course findCourse(Long id) {
        try {
            return courseRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        Preconditions.checkArgument(!Objects.isNull(id));
        if(courseRepository.existsById(id)) {
            Course courseToDelete = courseRepository.findById(id).get();
            courseRepository.delete(courseToDelete);
            return courseToDelete;
        }
        return null;
    }
}
