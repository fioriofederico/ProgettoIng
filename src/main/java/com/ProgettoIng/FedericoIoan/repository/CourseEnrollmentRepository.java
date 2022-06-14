package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollment;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollmentKey;
import com.ProgettoIng.FedericoIoan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, CourseEnrollmentKey> {
    List<CourseEnrollment> findCourseEnrollmentByCourse(Course course);
    List<CourseEnrollment> findCourseEnrollmentByStudent(User student);
}
