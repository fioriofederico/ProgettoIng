package com.federico_ioan.ProgettoIng.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findCourseById(Long id);
}
