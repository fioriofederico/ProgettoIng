package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseModuleDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long courseId;
}
