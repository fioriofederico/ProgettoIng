package com.federico_ioan.ProgettoIng.service.IService;


import com.federico_ioan.ProgettoIng.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> findCourses();

    Course findCourse(Long id);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course deleteCourse(Long id);

}
