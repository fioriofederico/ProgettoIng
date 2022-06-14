package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.CourseEnrollment;
import com.ProgettoIng.FedericoIoan.model.CourseEnrollmentKey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, CourseEnrollmentKey> {
}
