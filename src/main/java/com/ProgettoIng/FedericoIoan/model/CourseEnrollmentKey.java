package com.ProgettoIng.FedericoIoan.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
public class CourseEnrollmentKey implements Serializable {

    @Column(name = "course_id")
    Long courseId;

    @Column(name = "student_id")
    Long studentId;

    public CourseEnrollmentKey() {}

    public CourseEnrollmentKey(Long courseId, Long userId) {
        this.courseId = courseId;
        this.studentId = userId;
    }
}