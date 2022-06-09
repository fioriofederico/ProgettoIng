package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class CourseDto {

    @NotNull
    private String name;

    @NotNull
    private String duration;

    @NotNull
    private Long ownerId;
}
