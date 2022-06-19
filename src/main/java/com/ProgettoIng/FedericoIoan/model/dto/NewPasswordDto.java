package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class NewPasswordDto {

    @NotBlank
    @Size(min = 6, max = 100)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
    private String password;
}
