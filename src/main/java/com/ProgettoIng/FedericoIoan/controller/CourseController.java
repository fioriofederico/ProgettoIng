package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollment;
import com.ProgettoIng.FedericoIoan.model.Role;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.CourseDto;
import com.ProgettoIng.FedericoIoan.model.dto.RatingDto;
import com.ProgettoIng.FedericoIoan.service.CourseEnrollmentServiceImpl;
import com.ProgettoIng.FedericoIoan.service.CourseServiceImpl;
import com.ProgettoIng.FedericoIoan.service.PdfGenerateServiceImpl;
import com.ProgettoIng.FedericoIoan.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private CourseEnrollmentServiceImpl courseEnrollmentService;

	@Autowired
	private PdfGenerateServiceImpl pdfGenerateService;

	@GetMapping
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> getCourses() {
		try {
			List<Course> courses = courseService.findCourses();
			return ResponseEntity.ok(courses);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/manage/{role}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TUTOR')")
	public ResponseEntity<?> getUserCourses(@PathVariable String role) {
		try {
			User user = userService.getUserWithAuthorities().get();

			if (! user.getRoles().contains(Role.valueOf(role)))
				return ResponseEntity.badRequest().body("User is not " + role);

			switch (role) {
				case "tutor":
					List<Course> ownedCourses = courseService.findTutorCourses(user.getId());
					return ResponseEntity.ok(ownedCourses);
				case "student":
					List<Course> enrolledCourses = courseEnrollmentService.findEnrolledCourses(user.getId());
					return ResponseEntity.ok(enrolledCourses);
				default:
					return ResponseEntity.badRequest().body("Selected role does not exist");
			}

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<?> getCourse(@PathVariable Long courseId) {
		try {
			Course course = courseService.findCourse(courseId);
			return ResponseEntity.ok(course);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	@PreAuthorize("hasRole('TUTOR') or hasRole('ADMIN')")
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDto course) {
		try {
			Course createdCourse = courseService.createCourse(course);
			return ResponseEntity.ok(createdCourse);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{courseId}")
	@PreAuthorize("hasRole('TUTOR') or hasRole('ADMIN')")
	public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseDto course) {
		try {
			Course updatedCourse = courseService.updateCourse(courseId, course);
			return ResponseEntity.ok(updatedCourse);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{courseId}")
	@PreAuthorize("hasRole('TUTOR') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
		try {
			Course deletedCourse = courseService.deleteCourse(courseId);
			return ResponseEntity.ok(deletedCourse);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{courseId}/certificate")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> getCertificate(@PathVariable Long courseId) {
		try {
			Map<String, Object> certificateData = new HashMap<>();

			Course course = courseService.findCourse(courseId);
			User student = userService.getUserWithAuthorities().get();

			// Check if user is enrolled in the course
			if (! courseEnrollmentService.isEnrolled(courseId, student.getId()))
				return ResponseEntity.badRequest().body("User is not enrolled in the course");

			if (!courseEnrollmentService.isUserCertificateEnabled(courseId, student.getId()))
				return ResponseEntity.badRequest()
						.body("Certificate is not available, ask to the course owner for permission");

			certificateData.put("course", course);
			certificateData.put("student", student);

			String certificateName = student.getUsername() + "_" + course.getName() + ".pdf";

			certificateName = certificateName.replaceAll(" ", "_");

			Resource certificate = pdfGenerateService.generatePdfFile("certificate",
					certificateData, certificateName);

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + certificate.getFilename() + "\"")
					.body(certificate);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{courseId}/enroll/{userId}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> enrollUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) {
		try {
			CourseEnrollment courseEnrollment = courseEnrollmentService.enrollUser(courseId, userId);
			return ResponseEntity.ok(courseEnrollment);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{courseId}/unenroll/{userId}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> unenrollUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) {
		try {
			CourseEnrollment courseEnrollment = courseEnrollmentService.unenrollUser(courseId, userId);
			return ResponseEntity.ok(courseEnrollment);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{courseId}/rate")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> rateCourse(@PathVariable Long courseId, @Valid @RequestBody RatingDto rating) {
		try {
			User student = userService.getUserWithAuthorities().orElseThrow(Exception::new);

			CourseEnrollment courseEnrollment = courseEnrollmentService
					.rateCourse(courseId, student.getId(), rating.getRating());

			return ResponseEntity.ok(courseEnrollment);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{courseId}/enable_certificate/{userId}")
	@PreAuthorize("hasRole('TUTOR') or hasRole('ADMIN')")
	public ResponseEntity<?> enableCertificate(@PathVariable Long courseId, @PathVariable Long userId) {
		try {
			CourseEnrollment courseEnrollment = courseEnrollmentService.enableCertificate(courseId, userId);
			return ResponseEntity.ok(courseEnrollment);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{courseId}/students")
	@PreAuthorize("hasRole('TUTOR') or hasRole('ADMIN')")
	public ResponseEntity<?> getEnrolledStudents(@PathVariable Long courseId) {
		try {
			List<User> students = courseEnrollmentService.findEnrolledUsers(courseId);
			return ResponseEntity.ok(students);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
