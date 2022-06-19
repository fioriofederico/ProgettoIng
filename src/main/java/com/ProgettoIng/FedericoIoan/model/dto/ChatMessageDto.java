package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ChatMessageDto {

    @NotNull
    @Size(min = 1, max = 255)
    String message;
}
