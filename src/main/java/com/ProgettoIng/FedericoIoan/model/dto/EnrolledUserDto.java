package com.ProgettoIng.FedericoIoan.model.dto;


import lombok.Data;

@Data
public class EnrolledUserDto {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String email;

    private Boolean certificateEnabled;
}
