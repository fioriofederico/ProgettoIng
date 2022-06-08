package com.federico_ioan.ProgettoIng.Course;


import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public interface CourseService {

    List<Course> findCourses();

    Course findCourse(Long id);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course deleteCourse(Long id);

}
