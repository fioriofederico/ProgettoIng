package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollment;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollmentKey;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.repository.CourseEnrollmentRepository;
import com.ProgettoIng.FedericoIoan.repository.CourseRepository;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.service.IService.CourseEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    @Autowired
    CourseEnrollmentRepository courseEnrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    public CourseEnrollment enrollUser(Long courseId, Long userId) {

        if (!courseRepository.existsById(courseId))
            throw new RuntimeException("Course not found");

        if (!userRepository.existsById(userId))
            throw new RuntimeException("User not found");

        Course course = courseRepository.findById(courseId).get();
        User student = userRepository.findById(userId).get();

        CourseEnrollment courseEnrollment = new CourseEnrollment();

        courseEnrollment.setId(new CourseEnrollmentKey(courseId, userId));
        courseEnrollment.setCourse(course);
        courseEnrollment.setStudent(student);
        courseEnrollment.setCertificateEnabled(false);

        courseEnrollmentRepository.save(courseEnrollment);

        return courseEnrollment;
    }

    public CourseEnrollment unenrollUser(Long courseId, Long userId) {

        try {
            CourseEnrollment courseEnrollment = courseEnrollmentRepository
                    .findById(new CourseEnrollmentKey(courseId, userId)).orElseThrow(Exception::new);

            courseEnrollmentRepository.delete(courseEnrollment);

            return courseEnrollment;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CourseEnrollment rateCourse(Long courseId, Long userId, Integer rating) {
        try {
            CourseEnrollment courseEnrollment = courseEnrollmentRepository
                    .findById(new CourseEnrollmentKey(courseId, userId)).orElseThrow(Exception::new);

            courseEnrollment.setRating(rating);
            courseEnrollmentRepository.save(courseEnrollment);

            return courseEnrollment;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CourseEnrollment enableCertificate(Long courseId, Long userId) {
        try {
            CourseEnrollment courseEnrollment = courseEnrollmentRepository
                    .findById(new CourseEnrollmentKey(courseId, userId)).orElseThrow(Exception::new);

            // If certificate is disabled -> enable, otherwise disable
            courseEnrollment.setCertificateEnabled(!courseEnrollment.isCertificateEnabled());
            courseEnrollmentRepository.save(courseEnrollment);

            return courseEnrollment;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean isUserCertificateEnabled(Long courseId, Long userId) {
        try {
            CourseEnrollment courseEnrollment = courseEnrollmentRepository
                    .findById(new CourseEnrollmentKey(courseId, userId)).orElseThrow(Exception::new);

            return courseEnrollment.isCertificateEnabled();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
