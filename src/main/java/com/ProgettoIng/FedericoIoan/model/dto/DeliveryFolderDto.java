package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Data
public class DeliveryFolderDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    private LocalDateTime startDeliveryTime;

    @NotNull
    private LocalDateTime endDeliveryTime;
}
