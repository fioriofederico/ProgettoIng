package com.ProgettoIng.FedericoIoan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
public class VideoDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String url;

    private String endPointVimeo;

    @ManyToOne
    @JoinColumn(name = "course_module_id")
    @JsonIgnore
    private CourseModule courseModule;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateInsert;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateUpdate;
}
