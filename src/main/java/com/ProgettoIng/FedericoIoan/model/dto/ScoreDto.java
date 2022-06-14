package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
public class ScoreDto {

    @Min(1) @Max(10)
    private int score;
}
