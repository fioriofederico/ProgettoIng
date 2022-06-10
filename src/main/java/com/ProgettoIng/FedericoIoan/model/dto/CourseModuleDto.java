package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseModuleDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    // Url is set after the course module is created with the upload API
//    private String url;

    @NotNull
    private Long courseId;
}
