package com.ProgettoIng.FedericoIoan.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course_enrollments")
public class CourseEnrollment {

    @EmbeddedId
    @JsonIgnore
    CourseEnrollmentKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    User student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    // Each student needs to rate the course
    int rating;

    // The student needs to be evaluated by the tutor to get the certificate
    boolean certificateEnabled;
}
