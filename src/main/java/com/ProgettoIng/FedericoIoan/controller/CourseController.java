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
	public ResponseEntity<?> getCourses() {
		try {
			List<Course> courses = courseService.findCourses();
			return ResponseEntity.ok(courses);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/manage/{role}")
	public ResponseEntity<?> getUserCourses(@PathVariable String role) {
		try {
			// Get user from session
			User user = userService.getUserWithAuthorities().get();

			Set<String> userRoles = user.getRolesNames();

			if (!userRoles.contains(role))
				throw new RuntimeException("User is not authorized to access this area.");

			switch (role) {
				case "tutor":
					List<Course> ownedCourses = courseService.findTutorCourses(user.getId());
					return ResponseEntity.ok(ownedCourses);
				case "student":
					List<Course> enrolledCourses = courseEnrollmentService.findEnrolledCourses(user.getId());
					return ResponseEntity.ok(enrolledCourses);
				default:
					throw new RuntimeException("Selected role does not exixst.");
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
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDto course) {
		try {
			Course createdCourse = courseService.createCourse(course);
			return ResponseEntity.ok(createdCourse);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{courseId}")
	public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseDto course) {
		try {
			Course updatedCourse = courseService.updateCourse(courseId, course);
			return ResponseEntity.ok(updatedCourse);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
		try {
			Course deletedCourse = courseService.deleteCourse(courseId);
			return ResponseEntity.ok(deletedCourse);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{courseId}/certificate")
	public ResponseEntity<?> getCertificate(@PathVariable Long courseId) {
		try {
			// In data we store all the objectes we need for the certificate
			Map<String, Object> data = new HashMap<>();

			// Get course
			Course course = courseService.findCourse(courseId);

			// Get student
			User student = userService.getUserWithAuthorities().get();

			// Check if user certificate is enabled
			if (!courseEnrollmentService.isUserCertificateEnabled(courseId, student.getId()))
				throw new RuntimeException("Certificate is not available, ask to the course owner for permission");

			// Update data
			data.put("course", course);
			data.put("student", student);

			// Genereate pdf
			Resource certificate = pdfGenerateService.generatePdfFile("certificate", data, "certificate.pdf");

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + certificate.getFilename() + "\"")
					.body(certificate);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{courseId}/enroll/{userId}")
	public ResponseEntity<?> enrollUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) {
		try {
			CourseEnrollment courseEnrollment = courseEnrollmentService.enrollUser(courseId, userId);
			return ResponseEntity.ok(courseEnrollment);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{courseId}/unenroll/{userId}")
	public ResponseEntity<?> unenrollUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) {
		try {
			CourseEnrollment courseEnrollment = courseEnrollmentService.unenrollUser(courseId, userId);
			return ResponseEntity.ok(courseEnrollment);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{courseId}/rate")
	public ResponseEntity<?> rateCourse(@PathVariable Long courseId, @Valid @RequestBody RatingDto rating) {
		try {
			// Get user by session
			User student = userService.getUserWithAuthorities().orElseThrow(Exception::new);

			CourseEnrollment courseEnrollment = courseEnrollmentService
					.rateCourse(courseId, student.getId(), rating.getRating());

			return ResponseEntity.ok(courseEnrollment);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{courseId}/enable_certificate/{userId}")
	public ResponseEntity<?> enableCertificate(@PathVariable Long courseId, @PathVariable Long userId) {
		try {
			CourseEnrollment courseEnrollment = courseEnrollmentService.enableCertificate(courseId, userId);
			return ResponseEntity.ok(courseEnrollment);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{courseId}/students")
	public ResponseEntity<?> getEnrolledStudents(@PathVariable Long courseId) {
		try {
			List<User> students = courseEnrollmentService.findEnrolledUsers(courseId);
			return ResponseEntity.ok(students);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
