package com.ProgettoIng.FedericoIoan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course_modules")
public class CourseModule {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String url;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateInsert;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateUpdate;

    public CourseModule(String name, String description) {
        this.name = name;
        this.description = description;
    }
}