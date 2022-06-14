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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


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

    public List<User> findEnrolledUsers(Long courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(Exception::new);

            List<CourseEnrollment> courseEnrollments = courseEnrollmentRepository.findCourseEnrollmentByCourse(course);
            
            List<User> enrolledStudents = new ArrayList<User>();

            for (CourseEnrollment courseEnrollment: courseEnrollments) {
                enrolledStudents.add(courseEnrollment.getStudent());
            }

            return  enrolledStudents;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> findEnrolledCourses(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(Exception::new);

            List<CourseEnrollment> courseEnrollments = courseEnrollmentRepository.findCourseEnrollmentByStudent(user);

            List<Course> enrolledCourses = new ArrayList<Course>();

            for (CourseEnrollment courseEnrollment: courseEnrollments) {
                enrolledCourses.add(courseEnrollment.getCourse());
            }

            return  enrolledCourses;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
