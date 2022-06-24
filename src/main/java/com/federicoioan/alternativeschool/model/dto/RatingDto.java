package com.federicoioan.alternativeschool.model.dto;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class RatingDto {

    @NotNull
    @Min(1) @Max(10)
    private int rating;
}
