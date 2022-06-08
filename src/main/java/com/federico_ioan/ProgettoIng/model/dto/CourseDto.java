package com.federico_ioan.ProgettoIng.model.dto;


import javax.validation.constraints.NotNull;

public class CourseDto {

    @NotNull
    private String name;

    @NotNull
    private String duration;

    @NotNull
    private Long idOwner;


}
