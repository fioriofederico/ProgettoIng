package com.ProgettoIng.FedericoIoan.service.IService;


import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollment;
import com.ProgettoIng.FedericoIoan.model.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<Course> findCourses();

    Course findCourse(Long id);

    Course createCourse(CourseDto course);

    Course updateCourse(Long id, CourseDto course);

    Course deleteCourse(Long id);
}
