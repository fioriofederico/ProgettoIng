package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryFolderDto {

    private String name;

    private LocalDateTime startDeliveryTime;

    private LocalDateTime endDeliveryTime;

    private Long courseId;
}
