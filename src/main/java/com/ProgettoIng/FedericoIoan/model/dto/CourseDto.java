package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CourseDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 3, max = 255)
    private String description;

    // TODO: get owner from session ?
    @NotNull
    private Long ownerId;
}
