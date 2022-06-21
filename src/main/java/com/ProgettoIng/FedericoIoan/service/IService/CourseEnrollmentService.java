package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollment;

import java.util.List;

public interface CourseEnrollmentService {

    CourseEnrollment enrollUser(Long courseId, Long userId);

    CourseEnrollment unenrollUser(Long courseId, Long userId);

    CourseEnrollment rateCourse(Long courseId, Long userId, Integer rating);

    CourseEnrollment enableCertificate(Long courseId, Long userId);

    Boolean isUserCertificateEnabled(Long courseId, Long userId);

    List<EnrolledUserDto> findEnrolledUsers(Long courseId);

    List<Course> findEnrolledCourses(Long userId);

    boolean isEnrolled(Long courseId, Long studentId);
}
