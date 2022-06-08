package com.federico_ioan.ProgettoIng.Course;


import com.federico_ioan.ProgettoIng.User.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CourseDto {

    @NotNull
    private String name;

    @NotNull
    private String duration;

    @NotNull
    private Long idOwner;


}
